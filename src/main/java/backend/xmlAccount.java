package backend;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;

public class xmlAccount {
    //parse xml file class done

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

  public static void doSave(String filename, AccountDatabase bookstore) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(AccountDatabase.class);
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
