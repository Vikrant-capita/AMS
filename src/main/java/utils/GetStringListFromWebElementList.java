package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class GetStringListFromWebElementList {

	
	public List<String> getStringDataFromList(List<WebElement> eleList) {
		List<String> stringListData=new ArrayList<String>();
		for(WebElement list:eleList) {
			String stringData=list.getText();
			stringListData.add(stringData);
		}
		return stringListData;
	}
}
