package ud.example.soapapp.Clases;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class WebService {

    private static String NAMESPACE = "http://microsoft.com/webservices/";
    private static String URL = "https://euroconvert.azurewebsites.net/WebService1.asmx?WSDL";
    private static String SOAP_ACTION = "http://microsoft.com/webservices/";

    public static double CapturaEuroWs(String webMethName) {

        double resTxT = 0;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxT = Double.parseDouble(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resTxT;
    }

    public static double CambioEuroWs(String webMethName, String valord) {
        double resTxT1 = 0;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        request.addProperty("valor1",valord);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxT1 = Double.parseDouble(response.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resTxT1;
    }

    public static String HolaMundoWS(String name, String webMethName) {
        String resTxT = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        request.addProperty("nombre",name);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxT = response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resTxT;
    }


}