package gov.hhs.fha.nhinc.docretrieve.deferred.adapter.error;

import gov.hhs.fha.nhinc.common.nhinccommonadapter.AdapterDocumentRetrieveDeferredRequestErrorSecuredType;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayRetrieveSecuredRequestType;
import gov.hhs.healthit.nhin.DocRetrieveAcknowledgementType;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;

/**
 *
 * @author Ralph Saunders
 */
@WebService(serviceName = "AdapterDocRetrieveDeferredRequestErrorSecuredService", portName = "AdapterDocRetrieveDeferredRequestErrorSecuredPortSoap", endpointInterface = "gov.hhs.fha.nhinc.adapterdocretrievedeferredreqerrorsecured.AdapterDocRetrieveDeferredRequestErrorSecuredPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:adapterdocretrievedeferredreqerrorsecured", wsdlLocation = "WEB-INF/wsdl/AdapterDocRetreiveDeferredReqError/AdapterDocRetrieveDeferredReqErrorSecured.wsdl")
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class AdapterDocRetrieveDeferredReqErrorSecured {

    @Resource
    private WebServiceContext context;

    public DocRetrieveAcknowledgementType crossGatewayRetrieveRequestError(AdapterDocumentRetrieveDeferredRequestErrorSecuredType body) {
        return new AdapterDocRetrieveDeferredReqErrorSecuredImpl().respondingGatewayCrossGatewayRetrieve(body, context);
    }

}