import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class BusinessLogic {
    public static void main(String[] args) throws IOException {
        BusinessLogic logic = new BusinessLogic();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Input");
	    System.out.println("Example Input format - 2, Ricky, M, ricky@abc.com");
		String userInput = input.nextLine();
       logic.businessMethod("2,Ricky,M,ricky@abc.com");
    }

    void businessMethod(String str) throws IOException {
        List<Customer> customers = null;
        Long id = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            customers = Arrays.asList(mapper.readValue(Paths.get("data.json").toFile(), Customer[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String[] arrSplit = str.split(",");
        try {
            id = Long.parseLong(arrSplit[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Input");
        }
        boolean checkIdPresent=false;
        for(Customer customer : customers) {
            if(customer.getId() == id) {
                 checkIdPresent = true;
                System.out.println(customer.getId() + "," + customer.getName() + "," + customer.getGender() + "," +customer.getEmail()
                + "," + customer.getContactNumber() + "," + customer.getCreatedOn() + "," + customer.getAddress().getStreet()+ ","+customer.getAddress().getCity());
            }
        }
        if(checkIdPresent==false){
            System.out.println("Invalid Input");
        }
    }
}
