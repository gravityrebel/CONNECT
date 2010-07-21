/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.admindistribution;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.common.nhinccommon.NhinTargetCommunityType;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.CheckPolicyRequestType;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.CheckPolicyResponseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import gov.hhs.fha.nhinc.common.nhinccommonentity.RespondingGatewaySendAlertMessageType;
import gov.hhs.fha.nhinc.nhinclib.NullChecker;
import gov.hhs.fha.nhinc.policyengine.proxy.PolicyEngineProxy;
import gov.hhs.fha.nhinc.policyengine.proxy.PolicyEngineProxyObjectFactory;
import oasis.names.tc.xacml._2_0.context.schema.os.DecisionType;
import oasis.names.tc.emergency.edxl.de._1.EDXLDistribution;

/**
 *
 * @author dunnek
 */
public class AdminDistributionPolicyChecker {

    private Log log = null;
    public AdminDistributionPolicyChecker()
    {
        log = createLogger();
    }
    protected Log createLogger()
    {
        return LogFactory.getLog(getClass());
    }
    public boolean checkOutgoingPolicy (RespondingGatewaySendAlertMessageType request,String target) {
        log.debug("checking the policy engine for the new request to a target community");

        gov.hhs.fha.nhinc.transform.policy.AdminDistributionTransformHelper policyHelper;
        
        policyHelper = new gov.hhs.fha.nhinc.transform.policy.AdminDistributionTransformHelper();

        CheckPolicyRequestType checkPolicyRequest = policyHelper.transformEntityAlertToCheckPolicy(request, target);

        return invokePolicyEngine(checkPolicyRequest);
    }
    public boolean checkIncomingPolicy (EDXLDistribution request, AssertionType assertion) {
        log.debug("checking the policy engine for the new request to a target community");

        gov.hhs.fha.nhinc.transform.policy.AdminDistributionTransformHelper policyHelper;

        policyHelper = new gov.hhs.fha.nhinc.transform.policy.AdminDistributionTransformHelper();

        CheckPolicyRequestType checkPolicyRequest = policyHelper.transformNhinAlertToCheckPolicy(request, assertion);

        return invokePolicyEngine(checkPolicyRequest);
    }
    protected boolean invokePolicyEngine(CheckPolicyRequestType policyCheckReq) {
        boolean policyIsValid = false;

         /* invoke check policy */
        PolicyEngineProxyObjectFactory policyEngFactory = new PolicyEngineProxyObjectFactory();
        PolicyEngineProxy policyProxy = policyEngFactory.getPolicyEngineProxy();
        CheckPolicyResponseType policyResp = policyProxy.checkPolicy(policyCheckReq);

        /* if response='permit' */
        if (policyResp.getResponse() != null &&
                NullChecker.isNotNullish(policyResp.getResponse().getResult()) &&
                policyResp.getResponse().getResult().get(0).getDecision() == DecisionType.PERMIT) {
            log.debug("Policy engine check returned permit.");
            policyIsValid = true;
        } else {
            log.debug("Policy engine check returned deny.");
            policyIsValid = false;
        }

        return policyIsValid;
    }

}