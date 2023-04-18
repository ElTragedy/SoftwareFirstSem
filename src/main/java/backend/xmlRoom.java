package backend;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;

public class xmlRoom {

    //parse xml file class done

  public static RoomDatabase load(String s) {
    JAXBContext jaxbContext;
    RoomDatabase a = new RoomDatabase();
    try {
      jaxbContext = JAXBContext.newInstance(RoomDatabase.class);

      InputStream inputStream = xmlRoom.class.getClassLoader().getResourceAsStream(s);

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      a = (RoomDatabase) jaxbUnmarshaller.unmarshal(inputStream);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return a;
  }

  public static void doSave(String filename, RoomDatabase bookstore) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(RoomDatabase.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // Set JAXB properties to format the output XML nicely
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      // Save the XML to a file
      File file = new File(filename);
      jaxbMarshaller.marshal(bookstore, file);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }
  
}

