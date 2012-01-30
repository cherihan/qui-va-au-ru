package fr.esstin.ru;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CreerEtSupprimmerLesFichiersTextesServantDeProfilesUtilisateurs {

	public static void creer(String nom) {
		File g = new File(System.getProperty("user.dir" )+File.separatorChar+"profiles"+File.separatorChar);
		if (g.exists()){g.delete();};
		//g.createNewFile();
		String format = "dd/MM/yy H:mm:ss"; 
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 
		PrintWriter ecrivain=null;
		try {
			ecrivain = new PrintWriter(new BufferedWriter(new FileWriter(g)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ecrivain.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		ecrivain.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		ecrivain.println("<Document>");
		ecrivain.println("<name>Locations au "+formater.format(date)+"</name>");
		ecrivain.println("<description></description>");
		ArrayList list = new ArrayList();
		for (int i=0; i<list.size(); i++) {
			ecrivain.println("<Placemark>");
			ecrivain.println("<Style>");
			ecrivain.println("<IconStyle>");
			ecrivain.println("<Icon>");
			/*if(list.get(i).getType().equals("appartement")) {
				ecrivain.println("<href>http://maps.google.com/mapfiles/ms/icons/homegardenbusiness.png</href>");
			} else if (list.get(i).getType().equals("maison")) {
				ecrivain.println("<href>http://maps.google.com/mapfiles/ms/icons/rangerstation.png</href>");
			}
			ecrivain.println("</Icon>");
			ecrivain.println("</IconStyle>");
			ecrivain.println("</Style>");
			ecrivain.println("<description>");
			ecrivain.println("<![CDATA[");
			ecrivain.println("<div id=\"LocationDetail\">");
			ecrivain.println("<span id=\"title\"><h2>"+list.get(i).getTitre()+"</h2></span><br />");
			ecrivain.print("<span id=\"picture\">");
			if (!list.get(i).getPic().equals("")) {
				ecrivain.print("<a href=\""+list.get(i).getPic()
						+"\"><img src=\""+list.get(i).getPic()
						+"\" width =\"120\" /></a>");
			}
			ecrivain.println("</span><br />");
			ecrivain.print("<span id=\"comment\">");
			ecrivain.print(list.get(i).getDescription());
			ecrivain.println("</span><br />");
			ecrivain.println("Mise à jour le : ");
			ecrivain.println("<span id=\"date\">"+list.get(i).getDate()+"</span>");
			ecrivain.println("</div>");
			ecrivain.println("]]></description>");
			ecrivain.println("<Point>");
			ecrivain.println("<extrude>1</extrude>");
			ecrivain.println("<altitudeMode>relativeToGround</altitudeMode>");
			ecrivain.println("<coordinates>"+list.get(i).getLon()+","+list.get(i).getLat()+"</coordinates>");
			ecrivain.println("</Point>");																	
			ecrivain.println("</Placemark>");
		}*/
		ecrivain.println("</Document>");
		ecrivain.print("</kml>");
		ecrivain.close();
	}
}
}
