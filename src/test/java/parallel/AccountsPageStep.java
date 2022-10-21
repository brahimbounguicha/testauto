package parallel;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.Pages.AccountsPage;
import com.Pages.LoginPage;
import com.factory.Driverfactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AccountsPageStep {
	private LoginPage loginPage= new LoginPage(Driverfactory.getDriver());
	private AccountsPage accountsPage;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable credTable) {
	
	List<Map<String , String>> credList=	credTable.asMaps();
	String userName = credList.get(0).get("username");
	String password = credList.get(0).get("password");
	
	Driverfactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	accountsPage = loginPage.doLogin(userName, password);
	
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
	 String title =accountsPage.getAccountsPageTitle();
	 System.out.println("the title is : "+ title);
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable sectionTable) {
		List<String> expAccountSectionsList = sectionTable.asList();
		System.out.println("Expected account section list  : "+expAccountSectionsList);
	    List<String> actualAccountSectionsList = accountsPage.getAccountsSectionsList();
	    System.out.println("actual account section list  : "+actualAccountSectionsList);
	    Assert.assertTrue(expAccountSectionsList.containsAll(actualAccountSectionsList));
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedSectionCount) {
		int nbr = accountsPage.getAccountsSectionCount();
		System.out.println(" le nombre est : "+nbr);
    	Assert.assertTrue(accountsPage.getAccountsSectionCount()== expectedSectionCount);
	}

}
