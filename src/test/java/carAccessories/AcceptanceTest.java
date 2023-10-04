package carAccessories;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;



@RunWith(Cucumber.class)
@CucumberOptions(features="Features",plugin={"html:target/cucumber/wikipedia.html"},monochrome=true,snippets=SnippetType.CAMELCASE,glue={"carAccessories"})


public class AcceptanceTest {
public AcceptanceTest (Application b){
    b=new Application();
}
}
