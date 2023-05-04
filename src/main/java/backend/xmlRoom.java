package backend;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;

public class xmlRoom {

    //parse xml file class done

  /**
   * Loads a RoomDatabase object from an XML file given by its path.
   *
   * @param s the path to the XML file to be loaded
   * @return a RoomDatabase object loaded from the XML file
   */
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

  /**
   * Saves a RoomDatabase object to an XML file with the specified filename.
   *
   * @param filename the name of the file to save the XML to
   * @param bookstore the RoomDatabase object to be saved to XML
   */
  public static void doSave(String filename, RoomDatabase bookstore) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(RoomDatabase.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // Set JAXB properties to format the output XML nicely
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      // Save the XML to a file
      File file = new File("src/main/resources/" + filename);
      jaxbMarshaller.marshal(bookstore, file);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }
}

