package fr.esstin.ru.ws;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

@Deprecated
public class WSAccess {
	private static String NAMESPACE = "http://ru.esstin.fr/";
	private static String URL = "http://192.168.29.184:8080/ServiceWebQuiVaAuRu/WebServiceQuiVa?WSDL";

	public static String[] whosGoing() {
		String METHOD_NAME = "quiVaAuRu";
		String SOAP_ACTION = "\"http://ru.esstin.fr/ServiceWebQuiVaAuRu/quiVaAuRu\"";
		SoapSerializationEnvelope env;
		SoapObject request;

		env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		request = new SoapObject(NAMESPACE, METHOD_NAME);
		env.setOutputSoapObject(request);
		HttpTransportSE httpTransport = new HttpTransportSE(URL);

		try {
			httpTransport.call(SOAP_ACTION, env);
		} catch (Exception e) {
			Log.e("Erreur", e.toString());
		}
		Log.i("bodyIn", env.bodyIn.toString());
		SoapObject so = (SoapObject) env.bodyIn;
		String[] users = new String[so.getPropertyCount()];
		/*
		 * for (int i = 0; i < users.length; i++) { users[i] =
		 * so.getProperty(i).toString(); }
		 */
		users[0] = env.bodyIn.toString();
		return users;
	}

	public static void createUser() {
		String METHOD_NAME = "saveUser";
		String SOAP_ACTION = "\"http://ru.esstin.fr/ServiceWebQuiVaAuRu/saveUser\"";
		SoapSerializationEnvelope env;
		SoapObject request;

		env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		request = new SoapObject(NAMESPACE, METHOD_NAME);

		User user = new User();
		user.setCapacity(0);
		user.setCar(false);
		user.setCount(0);
		user.setFirstName("Lepori");
		user.setLastName("Philippe");
		user.setNickName("Philippe");
		user.setPswHash(42);
		user.setRu(true);
		user.setTakeCar(false);

		PropertyInfo pi = new PropertyInfo();
		pi.setName("user");
		pi.setValue(user);
		pi.setType(User.class);
		request.addProperty(pi);
		env.setOutputSoapObject(request);

		HttpTransportSE httpTransport = new HttpTransportSE(URL);

		try {
			httpTransport.call(SOAP_ACTION, env);
		} catch (Exception e) {
			Log.e("Erreur", e.toString());
		}
		Log.i("bodyIn", env.bodyIn.toString());
		SoapObject so = (SoapObject) env.bodyIn;
		Log.i("Résultat", (String) so.getProperty(0));
	}

	public static String[] GetBonjour() {
		String MethodName = "GetAllCategories";
		SoapObject response = InvokeMethod(URL, MethodName);
		return RetrieveFromSoap(response);

	}

	public static SoapObject MakeCall(String URL,
			SoapSerializationEnvelope Envelope, String NAMESPACE,
			String METHOD_NAME) {
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(NAMESPACE + METHOD_NAME, Envelope);
			SoapObject response = (SoapObject) Envelope.getResponse();
			return response;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static SoapObject GetSoapObject(String MethodName) {
		return new SoapObject(NAMESPACE, MethodName);
	}

	public static SoapSerializationEnvelope GetEnvelope(SoapObject Soap) {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(Soap);
		return envelope;
	}

	public static SoapObject InvokeMethod(String URL, String MethodName) {
		SoapObject request = GetSoapObject(MethodName);
		SoapSerializationEnvelope envelope = GetEnvelope(request);
		return MakeCall(URL, envelope, NAMESPACE, MethodName);
	}

	public static String[] RetrieveFromSoap(SoapObject soap) {
		String[] users = new String[soap.getPropertyCount()];
		for (int i = 0; i < users.length; i++) {
			SoapObject pii = (SoapObject) soap.getProperty(i);
			String user = "";
			user = pii.getPropertyAsString(0);
			/*
			 * category.CategoryId = Integer.parseInt(pii.getProperty(0)
			 * .toString()); category.Name = pii.getProperty(1).toString();
			 * category.Description = pii.getProperty(2).toString();
			 * categories[i] = category;
			 */
			users[i] = user;
		}
		return users;
	}

}
