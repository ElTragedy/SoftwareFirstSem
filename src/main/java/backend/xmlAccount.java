package backend;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;
import java.io.File;

public class xmlAccount {
    //parse xml file class done

  public AccountDatabase load(String s) {
    JAXBContext jaxbContext;
    AccountDatabase a = new AccountDatabase();
    s = "accounts.xml";
    try {
      jaxbContext = JAXBContext.newInstance(AccountDatabase.class);

      File file = new File(s);

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      a = (AccountDatabase) jaxbUnmarshaller.unmarshal(file);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return a;
  }

  public void doSave(String filename, AccountDatabase bookstore) {
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
