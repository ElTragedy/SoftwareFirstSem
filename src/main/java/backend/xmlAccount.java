package backend;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;

public class xmlAccount {
    //parse xml file class done

  /**
   * Loads an AccountDatabase from an XML file.
   *
   * @param s the filename of the XML file
   * @return an AccountDatabase object loaded from the specified XML file
   */
  public static AccountDatabase load(String s) {
    JAXBContext jaxbContext;
    AccountDatabase a = new AccountDatabase();
    try {
      jaxbContext = JAXBContext.newInstance(AccountDatabase.class);

      InputStream inputStream = xmlAccount.class.getClassLoader().getResourceAsStream(s);

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      a = (AccountDatabase) jaxbUnmarshaller.unmarshal(inputStream);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return a;
  }

  /**
   * Saves an AccountDatabase to an XML file.
   *
   * @param filename the name of the XML file to save to
   * @param bookstore the AccountDatabase object to save
   */
  public static void doSave(String filename, AccountDatabase bookstore) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(AccountDatabase.class);
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
