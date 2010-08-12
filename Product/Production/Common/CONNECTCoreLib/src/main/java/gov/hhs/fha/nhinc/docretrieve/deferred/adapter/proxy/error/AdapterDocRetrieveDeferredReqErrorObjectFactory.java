package gov.hhs.fha.nhinc.docretrieve.deferred.adapter.proxy.error;

import gov.hhs.fha.nhinc.properties.PropertyAccessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by
 * User: ralph
 * Date: Jul 26, 2010
 * Time: 1:28:35 PM
 */
public class AdapterDocRetrieveDeferredReqErrorObjectFactory {
    private Log log = null;

    private static final String CONFIG_FILE_NAME = "DocumentRetrieveDeferredProxyConfig.xml";
    private static final String BEAN_NAME_ADAPTER_DOC_RETRIEVE_DEFERRED_REQUEST = "adapterdocretrievedeferredrequesterror";
    private static ApplicationContext context = null;

     public AdapterDocRetrieveDeferredReqErrorObjectFactory()
     {
         log = LogFactory.getLog(getClass());

         context = new FileSystemXmlApplicationContext(PropertyAccessor.getPropertyFileURL() + CONFIG_FILE_NAME);
     }

     /**
      * Retrieve an adapter audit query implementation using the IOC framework.
      * This method retrieves the object from the framework that has an
      * identifier of "nhindocretrievedeferredrequest."
      *
      * @return AdapterAuditQueryProxy instance
      */
     public AdapterDocRetrieveDeferredReqErrorProxy getDocumentDeferredRequestProxy() {

         AdapterDocRetrieveDeferredReqErrorProxy result = null;

         if (context != null) {
             result = (AdapterDocRetrieveDeferredReqErrorProxy) context.getBean(BEAN_NAME_ADAPTER_DOC_RETRIEVE_DEFERRED_REQUEST);
         }
         else {
             log.error("Could not get context "+ BEAN_NAME_ADAPTER_DOC_RETRIEVE_DEFERRED_REQUEST + " from config file " +
                     PropertyAccessor.getPropertyFileURL() + CONFIG_FILE_NAME);
         }

         return result;
     }
}