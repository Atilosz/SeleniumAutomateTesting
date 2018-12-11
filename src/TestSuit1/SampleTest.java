package TestSuit1;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
//import org.apache.commons.lang3.time.DateUtils;
//import javafx.scene.layout.Region;
import static jdk.nashorn.internal.objects.NativeMath.round;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Attila
 */
public class SampleTest {

    public static WebDriver driver;
    public static Actions ac;

    //valtozok
    public static String gameID = "306";
    public static String gid = "irongirl";
    public static String gameName = "Iron Girl";
    public static String Gdd_default_denom = "0.08";
    public static String Gdd_default_coin = "1";
    public static String Gdd_default_line = "20";
    public static ArrayList<Double> Gdd_default_denominations = new ArrayList<>(Arrays.asList(0.01, 0.02, 0.03, 0.04, 0.05, 0.08, 0.10, 0.15, 0.25, 0.35, 0.50, 1.00, 2.00, 2.50, 5.00));
    public static ArrayList<Double> Gdd_default_high_denominations = new ArrayList<>(Arrays.asList(10.00, 20.00, 30.00, 40.00, 50.00));

    public SampleTest() {
    }
    //minden start elott beloggol GMT-be es beallitja, hogy ne legyen Splash screen

    @BeforeClass
    public static void setUpClass() throws Exception {
         System.setProperty("webdriver.chrome.driver", "C:\\Users\\Attila\\source\\repos\\FirstTest\\FirstTest\\bin\\Debug\\chromedriver.exe");
         driver = new ChromeDriver();
         driver.manage().window().maximize();
    }

    //bezarja a bongeszot amikor lefutottak a tesztek
    @AfterClass
    public static void tearDownClass() {
        // driver.quit();
    }

    @Before
    public void setUp() {
    }

    //visszamegy a reelhez
    @After
    public void tearDown() {
        //if (driver.findElement(By.id("menuImg")).isDisplayed() == true)
        // {
        // assertTrue(true);
        //}
        // driver.quit();
    }

    @Test
    public void asd() throws Exception {
        GMTConfiguration("game.showsplash", "0");

    }

    @Test
    public void CheckUserCanChangeDefaultBetInGMT() throws Exception {
        
        GMTLogin();
        GMT_Navigate_to_Denom_Setup();
        //ellenorzom, hogy a GDD szerinti ertekek vannak beallitva denom, coin és linenál
        String GMT_default_denom = driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[5]/span/span/span[1]")).getText();
        String GMT_default_coin = driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[6]/span/span/span[1]")).getText();
        String GMT_default_line = driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[7]/span/span/span[1]")).getText();

        if (!Gdd_default_denom.equals(GMT_default_denom) || !Gdd_default_coin.equals(GMT_default_coin) || !Gdd_default_line.equals(GMT_default_line)) {
            assertFalse(true);
            System.out.println("Hibás beállítás!");
        }
        //Navigálok a játékhoz
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=otepiapsexpredatdor&password");
        (new WebDriverWait(driver, 20000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuImg\"]")));  
        //Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"menuImg\"]")).click();
        //*[@id="soundButtonWrapper"]
        
//        Screen screen = new Screen();
//        Pattern image1 = new Pattern("C:\\images\\continue_btn.png");
//        screen.click(image1);
        
       // (new WebDriverWait(driver, 15000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")));
       System.out.println("Halt das Point!!!!");
       
        String Game_default_bet = driver.findElement(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")).getText();
        float FGame_default_bet = Float.parseFloat(Game_default_bet.substring(1));
        float FGdd_default_denom = Float.parseFloat(Gdd_default_denom);
        float FGdd_default_coin = Float.parseFloat(Gdd_default_coin);
        float FGdd_default_line = Float.parseFloat(Gdd_default_line);
        float szorzat = FGdd_default_denom * FGdd_default_coin * FGdd_default_line;
        BigDecimal result, result2;
        result = round(szorzat, 2);
        result2 = round(FGame_default_bet, 2);

        //ellenörzöm a game és a default betjet,  bet = denom * coin * line
        if (!result.equals(result2)) {
            assertFalse(true);
            System.out.println("Hibás default bet a jatékban!");
        }

        /**
         * * -Change the values for default denomination, coins and lines (if
         * not static) -Start the game again and check that the new
         * configurations are used *
         */
        GMT_Navigate_to_Denom_Setup();

        //beállitot ertekek helyesek e  
       // String[] asd = GMT_SetUp_DefaultBet_toMinimum_Values();

       // Game_bet_value_check(asd[0], asd[1], asd[2]);//get_denom,get_coin,get_lines);

        //itt vissza allitom a GDD szerinti ertekekre
        GMT_Navigate_to_Denom_Setup();

        GMT_SetUp_DefaultBet_aboutGDD_and_check_InGame();

    }

    @Test
    public void CheckSetBetinGMTisTheDefaultBetinTheGame() throws Exception {

        GMTLogin();
        GMT_Navigate_to_Denom_Setup();
        //itt beallitom a legkisebb ertekekre
        String get_denom = "";
        String get_coin = "";
        String get_lines = "";

        String[] asd = GMT_SetUp_DefaultBet_toMinimum_Values();

        Game_bet_value_check(asd[0], asd[1], asd[2]); //get_denom,get_coin,get_lines

        GMT_Navigate_to_Denom_Setup();
        GMT_SetUp_DefaultBet_aboutGDD_and_check_InGame();

    }

    @Test
    public void CheckUseCanChangeCoinsValueInGame() throws Exception {

        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=flyingpigsmobile&lang=en_GB&server=&practice=0&user=flyingpigsshooter&password");
        (new WebDriverWait(driver, 3000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"vbSpinBtn\"]")));

        WebElement coin_value_after = driver.findElement(By.xpath("//*[@id=\"coinsBalanceWrapper\"]/div[2]"));
        WebElement total_bet_after = driver.findElement(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]"));

        driver.findElement(By.xpath("//*[@id=\"menuImg\"]")).click();
        //csúszkáról veszem át az értéket
        WebElement slider_bet_value_following_after = driver.findElement(By.xpath("//div[@id='splitview_firsthalf']//div[@id='betview']//div[4]//div[3]//div[2]//div[3]//div[2]"));

        //x=10;
        WebElement slider = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[1]"));//kisebb csúszka
        //int width=slider.getSize().getWidth();
        Actions move = new Actions(driver);
        //move.moveToElement(slider, ((width*x)/100), 0).click();

        //elejére húzom
        move.moveToElement(slider, 0, 0).click();
        move.build().perform();
        //System.out.println("Slider moved");

//        for(int i=0;i<700;i+=10){
//        move.moveToElement(slider, i, 0).click();
//        move.build().perform();  
//        }
        String coin_value_before = driver.findElement(By.xpath("//*[@id=\"coinsBalanceWrapper\"]/div[2]")).getText();
        String total_bet_before = driver.findElement(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")).getText();
        String slider_bet_value_following_before = driver.findElement(By.xpath("//div[@id='splitview_firsthalf']//div[@id='betview']//div[4]//div[3]//div[2]//div[3]//div[2]")).getText();

        //ellenorzes
        if (!coin_value_after.getText().equals(coin_value_before) && !total_bet_after.getText().equals(total_bet_before) && !slider_bet_value_following_after.getText().equals(slider_bet_value_following_before)) {
            assertFalse(true);
        } else {
            assertTrue(true);
            //driver.quit();
        }

    }

    @Test
    public void VerifyInvalidDenominationsHandling() throws Exception {
        ac = new Actions(driver);

        if (!driver.getCurrentUrl().equals("http://pngqa19.playngonetwork.com:8080/configuration/configuration")) {
            GMTLogin();

            //atmegy a configuration oldalra 
            driver.navigate().to("http://pngqa19.playngonetwork.com:8080/configuration/configuration");
        }
        //fake denommal probálom ki
        String fake_denom = "3.89";
        GMTConfigurationDenom("game.denominations", "Iron Girl Mobile", fake_denom);

        Random rand = new Random();
        int n = rand.nextInt(500000) + 1;
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=otepiapsexpredatdor" + n + "&password");
        (new WebDriverWait(driver, 20000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")));
        driver.findElement(By.xpath("//*[@id='lobbyButtonWrapper']")).click();
        Thread.sleep(1000);
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=otepiapsexpredatdor" + n + "&password");
        (new WebDriverWait(driver, 20000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")));
        driver.findElement(By.xpath("//*[@id=\"menuImg\"]")).click();

        //a legelejére huzom
        WebElement left_slider_line_first = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[1]"));   //nem a dombot, hanem a kis részt huzgálom
        //ac.dragAndDropBy(left_slider_line_first, -48, 0).release().build().perform();
        System.out.println("Size: " + left_slider_line_first.getSize());

        /// Actions move = new Actions(driver);
        //move.moveToElement(left_slider_line_first, ((width*x)/100), 0).click();
        WebElement slider_bet_value_following = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[3]/div[2]"));
        //elejére húzom
//        for(int i=0;i>-50;i-=2){
//        move.moveToElement(left_slider_line_first, i, 0).click();
//        move.build().perform();
//         System.out.println("Value: " + slider_bet_value_following.getText());
//        }  

        ArrayList<String> InGame_bets_array = new ArrayList<String>();

        ac.dragAndDropBy(left_slider_line_first, -128, 0).release().build().perform();

        //kezzel szamoltam ki
        for (int i = -100; i < 266; i += 1) {
            ac.dragAndDropBy(left_slider_line_first, i, 0).release().build().perform();

//            if (    !slider_bet_value_following.getText().equals(fake_denom) ) {  
//            } else {
//                    System.out.println("Hiba!!!");
//                    //assertFalse(true);
//                    //driver.quit();
//                }
//         }
            if (InGame_bets_array.contains(slider_bet_value_following.getText()) == true) {

            } else {
                //ertek hosszadasa az arrayListemhez
                InGame_bets_array.add(slider_bet_value_following.getText());
                //System.out.println(slider_bet_value_following.getText().substring(0, (slider_bet_value_following.getText().lastIndexOf("\n") ))); 

                //a ket ertesz osszehasonlitasa
                if (slider_bet_value_following.getText().equals(fake_denom)) {
                    System.out.println("Hiba!!!");
                    System.out.println("Slider string erteke: " + slider_bet_value_following.getText());

                    assertFalse(true);
                    driver.quit();
                } else {

                }

            }

        }

        assertTrue(true);
        driver.quit();

        ////PALI CSINÁLTA FONTOSSS!!!!
//    WebElement proba = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[1]"));
//    int totalPercentage;
//    for (int i = 1; i < gddBets.length; i++) {
//    totalPercentage = (int) ( (Double)(100.000 / (gddBets.length - 1)) * i);
//    ac.moveToElement(proba).clickAndHold(proba).moveByOffset((int)(widthElement * (totalPercentage / 100.000)), 0).release().build().perform();
//    }
    }

    @Test
    public void CheckDefaultStartingBet() throws Exception {
        driver = new ChromeDriver();
        ac = new Actions(driver);
        driver.manage().window().maximize();
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=seleniumtestathdhlasss&password");
        Thread.sleep(20000);
        String Gdd_DefaultBet_€ = "€1.60";
        //String Gdd_DefaultBet_IDR ="IDR 16000.00";
        //String Gdd_DefaultBet_HUF ="HUF 480.00";
        String Get_Game_DefaultBet = driver.findElement(By.id("moneyBetWrapper")).getText();

        assertTrue(Get_Game_DefaultBet.toLowerCase().contains(Gdd_DefaultBet_€));
    }

    @Test
    public void CheckDefaultBets() throws Exception {
        //drag'n'drop the slider
        //driver = new ChromeDriver();
        ac = new Actions(driver);
        driver.manage().window().maximize();
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=otepiapsexpredatdor&password");
        Thread.sleep(20000);
        //klikk a menure
        driver.findElement(By.xpath("//*[@id=\"menuImg\"]")).click();
        //GDD-s ertekek
        String Gdd_bets_array[] = {"0.20", "0.40", "0.60", "0.80", "1.00", "1.60", "2.00", "3.00", "5.00", "7.00", "10.00", "20.00", "40.00", "50.00", "100.00"};
        //jatek betsjei
        ArrayList<String> InGame_bets_array = new ArrayList<String>();

        //slider de nem jo
        //WebElement slider = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]"));
        //ac.dragAndDropBy(slider, 0, 0).release().build().perform();
        //slider.click();
        //ertek lekerese
        //WebElement slider_bet_value_before = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[3]"));
        WebElement left_slider_line_first = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[1]"));   //nem a dombot, hanem a kis részt huzgálom

        //a legelejére huzom
        ac.dragAndDropBy(left_slider_line_first, -128, 0).release().build().perform();

        //lekered a nagy divet amiben benne van a slider (//*[@id="betview"]/div[4]/div[3]/div[2])
        //WebElement div = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]"));                                              
        //Dimension divi= div.getSize();
        //int divi_hossza = divi.getWidth();
        //System.out.println("divi_hossza: " + divi_hossza);
        WebElement slider_bet_value_following = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[3]/div[2]"));

        //WebElement Proba_slider_bet_value_following = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[3]/div[2]"));
        //System.out.println("Proba: " + Proba_slider_bet_value_following.getText());
        //String összehasonmlitas equalssal
        int cnn = 0;

        //kezzel szamoltam ki
        for (int i = -100; i < 266; i += 17) {
            ac.dragAndDropBy(left_slider_line_first, i, 0).release().build().perform();

            if (InGame_bets_array.contains(slider_bet_value_following.getText()) == true) {

            } else {
                //ertek hosszadasa az arrayListemhez
                InGame_bets_array.add(slider_bet_value_following.getText());
                //System.out.println(slider_bet_value_following.getText().substring(0, (slider_bet_value_following.getText().lastIndexOf("\n") ))); 

                //a ket ertesz osszehasonlitasa
                if (slider_bet_value_following.getText().equals(Gdd_bets_array[cnn])) {
                    //System.out.println("jeeee ");
                    //System.out.println("slider string:: " + slider_bet_value_following.getText().substring(0, (slider_bet_value_following.getText().lastIndexOf("\n"))) );
                    //System.out.println("Gdd_bets_array string hossza: " + Gdd_bets_array[cnn]);
                    cnn++;

                } else {
                    System.out.println("Hiba!!!");
                    System.out.println("Slider string erteke: " + slider_bet_value_following.getText());
                    System.out.println("Ignore_Gdd_bets_array string erteke: " + Gdd_bets_array[cnn]);
                    assertFalse(true);
                    driver.quit();

                }

            }

        }

        assertTrue(true);

    }

    @Test
    public void VerifyHighDenom() throws InterruptedException {

        GMTLogin();
        //GMTConfiguration("game.maxslotbet", "20000");

        GMTConfigurationHighDenom("game.denominations", Gdd_default_denominations);
    }

    //Game ID check
    @Test
    public void CheckGid() {
        String url = driver.getCurrentUrl();

        assertTrue(url.contains(gid));
    }

    @Test
    public void CheckAvailableDenom() throws Exception {
        /*GMTLogin();
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/casino/gameconfiguration");
        //prduct groupra kattintok
        driver.findElement(By.xpath("//*[@id=\"entryform\"]/div[1]/div/div/div[1]/div[1]/span/span/span[1]")).click();
        //stubbedot választja ki
        driver.findElement(By.xpath("//*[@id=\"productGroupSelector_listbox\"]/li[2]")).click();
//        driver.findElement(By.id("productGroupSelector_listbox"));
        driver.findElement(By.xpath("//*[@id=\"tabstrip\"]/ul/li[3]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[@id=\"DenominationGrid\"]/div[1]/div/table/thead/tr/th[1]/a[1]/span")).click();

        int divNO = 50;
        while(true)
        {
            if (!driver.findElements(By.xpath(
                    "//*[@id=\"body\"]/div["+(++divNO)+"]/form/div[1]/span[2]/span")).isEmpty())
                break;
        }
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[" + divNO + "]/form/div[1]/span[2]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[" + divNO + "]/form/div[1]/span[2]/span/input[2]")).sendKeys(gameID);
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[" + divNO + "]/form/div[1]/div[2]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"DenominationGrid\"]/div[2]/table/tbody/tr")).click();
        for (int i = 0; i < driver.findElements(By.className("toggle")).size(); i++) {
            WebElement denomButton = driver.findElements(By.className("toggle")).get(i);
            if(!driver.findElement(By.id(denomButton.getText())).isSelected())
            {
              denomButton.click();
            }
        }
        */
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=apacukafundalu&password");
        Thread.sleep(20000);
        //klikk a menure
        driver.findElement(By.xpath("//*[@id=\"menuImg\"]")).click();
        
        
        //GDD-s ertekek
        String Gdd_bets_array[] = {"0.20", "0.40", "0.60", "0.80", "1.00", "1.20", "1.40", "1.60", "1.80", "2.00", "3.00", "4.00", "5.00", "6.00", "7.00", "8.00", "9.00", "10.00", "15.00", "20.00", "30.00", "40.00", "50.00", "60.00", "70.00", "80.00", "90.00", "100.00"};
        //jatek betsjei
        ArrayList<String> InGame_bets_array = new ArrayList<>(Arrays.asList("0.20", "0.40", "0.60", "0.80", "1.00", "1.20", "1.40", "1.60", "1.80", "2.00", "3.00", "4.00", "5.00", "6.00", "7.00", "8.00", "9.00", "10.00", "15.00", "20.00", "30.00", "40.00", "50.00", "60.00", "70.00", "80.00", "90.00", "100.00"));

        //ertek lekerese
        WebElement left_slider_line_first = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[1]"));
        ac = new Actions(driver);
        //a legelejére huzom a slidert
        //ac.dragAndDropBy(left_slider_line_first, -128, 0).release().build().perform();

        WebElement slider_bet_value_following = driver.findElement(By.xpath("//*[@id=\"betview\"]/div[4]/div[3]/div[2]/div[3]/div[2]"));

        int cnn = 0;
        WebElement webElem = driver.findElement(By.xpath("//*[@id='betview']/div[4]/div[3]/div[2]"));

        ac.moveToElement(webElem,-(webElem.getSize().width/2),0).release().build().perform();
        for (int i = -(webElem.getSize().width/2); i < webElem.getSize().width/2; i += (webElem.getSize().width/40)) {
            /*ac.moveByOffset(5,0);
            ac.click().release().build().perform();*/
            //ac.moveToElement(webElem, i, 0).release().build().perform();
            ac.dragAndDropBy(webElem, i, 0).release().build().perform();
            if (!InGame_bets_array.contains(slider_bet_value_following.getText())) {
                assertTrue(false);
            } else {
                //ertek hosszadasa az arrayListemhez
                InGame_bets_array.add(slider_bet_value_following.getText());
                System.out.println(slider_bet_value_following.getText());

                //a ket ertek osszehasonlitasa
                if (slider_bet_value_following.getText().equals(Gdd_bets_array[cnn])) {
                    //System.out.println("jeeee ");
                    //System.out.println("slider string:: " + slider_bet_value_following.getText().substring(0, (slider_bet_value_following.getText().lastIndexOf("\n"))) );
                    //System.out.println("Gdd_bets_array string hossza: " + Gdd_bets_array[cnn]);
                    cnn++;

                } else {
                    System.out.println("Hiba!!!");
                    System.out.println("Slider string erteke: " + slider_bet_value_following.getText());
                    System.out.println("Ignore_Gdd_bets_array string erteke: " + Gdd_bets_array[cnn]);
                    //assertFalse(true);
                    //driver.quit();
                }
            }
            assertTrue(true);
        }
    }

    public static void GMTLogin() {
        //megnyitja a GMT-t
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080");

        //beloggol
        if (driver.findElements(By.name("UserName")).size() > 0) {
            driver.findElement(By.name("UserName")).sendKeys("pngoperation");
            driver.findElement(By.name("Password")).sendKeys("playngo");
            driver.findElement(By.id("loginButton")).click();
        }
    }

    public static void GMT_ShowsPlash_SetUp() throws Exception {
        driver.findElement(By.id("NewBtn")).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[1]/div[1]/span/span/span[2]")).click();

        //var fel masodpercet
        Thread.sleep(500);

        //rakeres a showsplashre
        driver.findElement(By.xpath("//*[@id=\'ConfigurationName-list\']/span/input")).sendKeys("game.showsplash");

        Thread.sleep(500);

        //kivaslasztja es rakattint a product group dropdownra
        driver.findElement(By.xpath("//*[@id=\'Name\']")).click();
        driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[1]/div[2]/span/span/span[1]")).click();

        Thread.sleep(500);

        //stubbedot valaszt majd rakattint a Game dropdownra
        driver.findElements(By.xpath("//*[@id=\"AddProductGroup_listbox\"]/li")).get(1).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[2]/div[2]/span/span")).click();

        Thread.sleep(500);

        //beirja a game id-t
        driver.findElement(By.xpath("//*[@id=\"AddGames-list\"]/span/input")).sendKeys(gameID);

        Thread.sleep(500);

        //kivalatsztja a jatekot, hozzaadja a beallitast
        driver.findElements(By.xpath("//*[@id=\"AddGames_listbox\"]/li")).get(0).click();
        driver.findElement(By.id("AddBtn")).click();

    }

    public static void GMTDeletIfExists(String settingName) throws Exception {
        GMTLogin();

        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/configuration/configuration");

        Thread.sleep(500);

        //game id-ra szur
        driver.findElement(By.xpath("//*[@id=\"configurationGrid\"]/div[1]/div/table/thead/tr/th[5]/a[1]")).click();

        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id=\"body\"]/div[11]/form/div[1]/span[2]/span/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[11]/form/div[1]/span[2]/span/input[2]")).sendKeys(gameID);
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[11]/form/div[1]/div[2]/button[1]")).click();

        Thread.sleep(500);

        //setting name-re szur
        driver.findElement(By.xpath("//*[@id=\"configurationGrid\"]/div[1]/div/table/thead/tr/th[7]/a[1]")).click();

        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id=\"body\"]/div[12]/form/div[1]/input")).sendKeys(settingName);
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[12]/form/div[1]/div[2]/button[1]")).click();

        Thread.sleep(1000);

        //ha van talalat akkor kitorli
        if (driver.findElements(By.xpath("//*[@id=\"configurationGrid\"]/div[2]/table/tbody/tr")).size() > 0) {
            driver.findElement(By.xpath("//*[@id=\"configurationGrid\"]/div[2]/table/tbody/tr")).click();
            driver.findElement(By.id("DeleteBtn")).click();

            Thread.sleep(1000);

            driver.findElement(By.id("yesButton")).click();
        }
    }

    public static void GMTConfiguration(String settingsName, String settingsValue) {
        GMTLogin();

        //atmegy a configuration oldalra 
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/configuration/configuration");
        driver.findElement(By.id("NewBtn")).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[1]/div[1]/span/span/span[2]")).click();

        //var fel masodpercet
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //rakeres a setting name-re 
        driver.findElement(By.xpath("//*[@id=\'ConfigurationName-list\']/span/input")).sendKeys(settingsName);

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //kivaslasztja es rakattint a product group dropdownra
        driver.findElement(By.xpath("//*[@id=\'Name\']")).click();
        driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[1]/div[2]/span/span/span[1]")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //stubbedot valaszt majd rakattint a Game dropdownra
        driver.findElements(By.xpath("//*[@id=\"AddProductGroup_listbox\"]/li")).get(1).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[2]/div[2]/span/span")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //beirja a game id-t
        driver.findElement(By.xpath("//*[@id=\"AddGames-list\"]/span/input")).sendKeys(gameID);

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //kivalatsztja a jatekot, hozzaadja a beallitast es elnavigal az oldalra 
        driver.findElements(By.xpath("//*[@id=\"AddGames_listbox\"]/li")).get(0).click();

        //ha true vagy false a beallitas erteke akkor checkeli/uncheckeli a checkboxot
        if (settingsValue.equals("true") || settingsValue.equals("false")) {
            if (settingsValue.equals("true")) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/input")).click();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } else {
            //amennyiben egyeb szoveg akkor megnezi milyen fieldet talal
            if (driver.findElements(By.id("StringCsvTxtBox")).size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/textarea")).sendKeys(settingsValue);

            } else if (driver.findElements(By.xpath("//*[@id=\"updateValueForm\"]/div[3]/div[1]/div/div/div[2]/span/span/input[1]")).size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/span/span/input[1]")).sendKeys(settingsValue);

            } else if (driver.findElements(By.id("UrlEmailTxtBox")).size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/input")).sendKeys(settingsValue);

            }

        }

        //hozzaad
        driver.findElement(By.id("AddBtn")).click();
    }

    public static void GMTConfigurationDenom(String settingsName, String settingsValue, String fake_denom) throws InterruptedException {
        GMTLogin();

        //atmegy a configuration oldalra 
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/configuration/configuration");
        driver.findElement(By.id("NewBtn")).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[1]/div[1]/span/span/span[2]")).click();

        //var fel masodpercet
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //rakeres a setting name-re 
        driver.findElement(By.xpath("//*[@id=\'ConfigurationName-list\']/span/input")).sendKeys(settingsName);

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //kivaslasztja es rakattint a product group dropdownra
        driver.findElement(By.xpath("//*[@id=\'Name\']")).click();
        driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[1]/div[2]/span/span/span[1]")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //stubbedot valaszt majd rakattint a Game dropdownra
        driver.findElements(By.xpath("//*[@id=\"AddProductGroup_listbox\"]/li")).get(1).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[2]/div[2]/span/span")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //beirja a game id-t
        driver.findElement(By.xpath("//*[@id=\"AddGames-list\"]/span/input")).sendKeys(gameID);

        Thread.sleep(500);

        //kivalatsztja a jatekot, hozzaadja a beallitast es elnavigal az oldalra 
        driver.findElements(By.xpath("//*[@id=\"AddGames_listbox\"]/li")).get(0).click();

        //ha true vagy false a beallitas erteke akkor checkeli/uncheckeli a checkboxot
        if (settingsValue.equals("true") || settingsValue.equals("false")) {
            if (settingsValue.equals("true")) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/input")).click();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } else {
            //amennyiben egyeb szoveg akkor megnezi milyen fieldet talal
            if (driver.findElements(By.id("StringCsvTxtBox")).size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/textarea")).sendKeys(settingsValue);

            } else if (driver.findElements(By.xpath("//*[@id=\"updateValueForm\"]/div[3]/div[1]/div/div/div[2]/span/span/input[1]")).size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/span/span/input[1]")).sendKeys(settingsValue);

            } else if (driver.findElements(By.id("UrlEmailTxtBox")).size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[3]/div[1]/div/div/div[2]/input")).sendKeys(settingsValue);

            }

        }
        WebElement add = driver.findElement(By.xpath("//*[@id=\"deomination-grid\"]/div[1]/a"));
        if (!add.getText().equals("Add new record")) {

        } else {
            add.click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"deomination-grid\"]/div[3]/table/tbody/tr/td[1]")).click();
            driver.findElement(By.xpath("//*[@id='deomination-grid']/div[3]/table/tbody/tr/td[1]/span/span/input[2]")).sendKeys(fake_denom);

        }

        //hozzaad
        driver.findElement(By.id("AddBtn")).click();
    }

    //meglevo GMT beallitasok ertekeinek szerkesztese
    public static void GMTEdit(String settingName, String newValue) {
        //login
        GMTLogin();

        //configuration oldalra navigal
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/configuration/configuration");

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        // raszur a game id-ra
        driver.findElement(By.xpath("//*[@id=\"configurationGrid\"]/div[1]/div/table/thead/tr/th[5]/a[1]")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        driver.findElement(By.xpath("//*[@id=\"body\"]/div[12]/form/div[1]/span[2]/span/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[12]/form/div[1]/span[2]/span/input[2]")).sendKeys(gameID);
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[12]/form/div[1]/div[2]/button[1]")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //raszur a setting name-re
        driver.findElement(By.xpath("//*[@id=\"configurationGrid\"]/div[1]/div/table/thead/tr/th[7]/a[1]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[13]/form/div[1]/input")).sendKeys(settingName);
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[13]/form/div[1]/div[2]/button[1]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        if (driver.findElements(By.xpath("//*[@id=\"configurationGrid\"]/div[2]/table/tbody/tr")).size() > 0) {
            //ha van talalat akkor rakattint
            driver.findElement(By.xpath("//*[@id=\"configurationGrid\"]/div[2]/table/tbody/tr")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            //ha true vagy false a beallitas erteke akkor checkeli/uncheckeli a checkboxot
            if (newValue.equals("true") || newValue.equals("false")) {
                WebElement checkBox = driver.findElement(By.id("BoolTxtBox"));
                Boolean b = !(newValue.equals(String.valueOf(checkBox.isSelected())));

                if (b == true) {
                    driver.findElement(By.xpath("//*[@id=\"updateValueForm\"]/div[3]/div[1]/div/div/div[2]/input")).click();
                    driver.findElement(By.id("UpdateBtn")).click();
                }
            } else {
                //amennyiben egyeb szoveg akkor megnezi milyen fieldet talal
                if (driver.findElements(By.id("StringCsvTxtBox")).size() > 0) {
                    driver.findElement(By.xpath("//*[@id=\"updateValueForm\"]/div[3]/div[1]/div/div/div[2]/textarea")).sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue);

                } else if (driver.findElements(By.xpath("//*[@id=\"updateValueForm\"]/div[3]/div[1]/div/div/div[2]/span/span/input[1]")).size() > 0) {
                    driver.findElement(By.xpath("//*[@id=\"updateValueForm\"]/div[3]/div[1]/div/div/div[2]/span/span/input[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue);

                } else if (driver.findElements(By.id("UrlEmailTxtBox")).size() > 0) {
                    driver.findElement(By.xpath("//*[@id=\"updateValueForm\"]/div[3]/div[1]/div/div/div[2]/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue);

                }
                //vegul updatel
                driver.findElement(By.id("UpdateBtn")).click();
            }
        } else { //ha nem talal olyan beallitast amire ra van szurve akkor hozzaadja
            GMTConfiguration(settingName, newValue);
        }
    }

    /**
     *
     * @param username user name for specific players - if "" (empty string)
     * then fg'll sent for all players
     * @param noOfFG number of free games - if 0 then 10 fg'll be sent
     * @param denom denomination - if 0 then setted to default
     */
    public static void GMTFreeGameSetup(String username, int noOfFG, double denom) {
        GMTLogin();
        Random r = new Random();
        //free gamekhez navigal
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/freegamesetup");
        driver.findElement(By.id("createFreeGameButton")).click();
        driver.findElement(By.id("name")).sendKeys(gid + (r.nextInt(1000 - 1) + 1)); //random szamot tesz a game id utan
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //ha van felhasznalonev akkor beallitja, ha nincs akkor defaultba mindenkinek fog kuldeni fg-t
        if (!username.equals("")) {

            driver.findElement(By.xpath("//*[@id=\"playerSelectionForm\"]/div[1]/label[3]")).click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            driver.findElement(By.id("FindExternalId")).sendKeys(username);
            driver.findElement(By.id("FindPlayerBtn")).click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            driver.findElement(By.xpath("//*[@id=\"foundUserGrid\"]/table/tbody/tr")).click();
        }
        //megkeresi a jatekot
        driver.findElement(By.id("search")).sendKeys(gameName);
        driver.findElement(By.id("search")).sendKeys(Keys.RETURN);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.xpath("//*[@id=\"gamesGrid\"]/table/tbody/tr/td[1]")).click();
        //ha meg van adva mennyi fg-t akarsz beallitani akkor annyit allit, egyebkent marad defaulton
        if (noOfFG != 0 && noOfFG > 0) {
            driver.findElement(By.xpath("//*[@id=\"gameSelectionForm\"]/div[3]/div[1]/div/div[1]/span/span/input[1]")).click();
            driver.findElement(By.id("Rounds")).sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(noOfFG));
        }
        //ugyanigy a denommal
        if (denom != 0 && denom > 0) {
            driver.findElement(By.xpath("//*[@id=\"gameSelectionForm\"]/div[2]/div[1]/div/div[3]/span[1]")).click();
            List<WebElement> denominations = driver.findElements(By.xpath("//*[@id=\"Denominations_listbox\"]/li"));
            for (WebElement denomination : denominations) {
                if (denomination.getText().equals(String.valueOf(denom))) {
                    denomination.click();
                    break;
                }
            }
        }
        driver.findElement(By.id("showSummary")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //megtortenhet, hogy ketszer ugyanazt a szamot adja ki a random number generator, ilyenkor ujrahivjuk ezt a fugvenyt
        if (driver.findElements(By.id("start")).size() > 0) {
            DateFormat dateFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //Date dateStart = DateUtils.addDays(new Date(), -5);
            //Date dateEnd = DateUtils.addDays(new Date(), +5);

            //driver.findElement(By.id("start")).sendKeys(dateFromat.format(dateStart));
            //driver.findElement(By.id("end")).sendKeys(dateFromat.format(dateEnd));
        } else {
            GMTFreeGameSetup(username, noOfFG, denom);
        }

        driver.findElement(By.id("saveFreeGame")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //nem default denomnal le OK-ozza a felugro ablakot
        if (driver.findElements(By.id("NextBtn")).size() > 0) {
            driver.findElement(By.id("NextBtn")).click();
        }
    }

    public static void GMTSet_Denomination(String dennom) throws InterruptedException {

        //majd ha jó lesz a denomra klikk, utána ezt kell megírni
    }

    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public static void GMT_Navigate_to_Denom_Setup() throws InterruptedException {
          
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/casino/gameconfiguration");
        //prduct groupra kattintok
        driver.findElement(By.xpath("//*[@id=\"entryform\"]/div[1]/div/div/div[1]/div[1]/span/span/span[1]")).click();
        Thread.sleep(500);
        //(new WebDriverWait(driver, 300)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"productGroupSelector_listbox\"]/li[2]")) );
        //stubbedot választja ki
        driver.findElement(By.xpath("//*[@id=\"productGroupSelector_listbox\"]/li[2]")).click();
        driver.findElement(By.id("productGroupSelector_listbox"));
        Thread.sleep(500);
        //(new WebDriverWait(driver, 300)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tabstrip\"]/ul/li[1]")) );
        driver.findElement(By.xpath("//*[@id=\"tabstrip\"]/ul/li[1]")).click();
        Thread.sleep(500);
        //(new WebDriverWait(driver, 300)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"SlotDefaultGrid\"]/div[1]/div/table/thead/tr/th[1]/a[1]/span")) );
        driver.findElement(By.xpath("//*[@id=\"SlotDefaultGrid\"]/div[1]/div/table/thead/tr/th[1]/a[1]/span")).click();
        int var_elem_size_1 = driver.findElements(By.xpath("//*[@id=\"body\"]/div[54]/form/div[1]/span[2]/span")).size();
        int var_elem_size_2 = driver.findElements(By.xpath("//*[@id=\"body\"]/div[48]/form/div[1]/span[2]/span")).size();
        Thread.sleep(500);
        //egy kis hack
        int divNO = 0;
        if (var_elem_size_1 == 0) {
            if (var_elem_size_2 > 0) {
                divNO = 48;

            } else {
                divNO = 51;
            }
        } else {
            divNO = 54;
        }
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[" + divNO + "]/form/div[1]/span[2]/span")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[" + divNO + "]/form/div[1]/span[2]/span/input[2]")).sendKeys(gameID);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[" + divNO + "]/form/div[1]/div[2]/button[1]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"SlotDefaultGrid\"]/div[2]/table/tbody/tr")).click();
        Thread.sleep(500);
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            if (element.isDisplayed()) {
                return element.isDisplayed();
            }
        } catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }

    public boolean Game_bet_value_check(String denom, String coin, String line) throws InterruptedException {
        Random rand = new Random();
        int n = rand.nextInt(500000) + 1;
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=otepiapsexpredatdor" + n + "&password");
        (new WebDriverWait(driver, 20000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")));
        driver.findElement(By.xpath("//*[@id='lobbyButtonWrapper']")).click();
        Thread.sleep(1000);
        driver.navigate().to("http://pngqa19.playngonetwork.com/casino/PlayMobile?pid=2&gid=irongirlmobile&lang=en_GB&server=&practice=0&user=otepiapsexpredatdor" + n + "&password");
        (new WebDriverWait(driver, 20000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")));

        String Game_default_bet = driver.findElement(By.xpath("//*[@id=\"moneyBetWrapper\"]/div[2]")).getText();
        float FGame_default_bet = Float.parseFloat(Game_default_bet.substring(1));
        float FGdd_default_denom = Float.parseFloat(denom);
        float FGdd_default_coin = Float.parseFloat(coin);
        float FGdd_default_line = Float.parseFloat(line);

        float szorzat = FGdd_default_denom * FGdd_default_coin * FGdd_default_line;
        BigDecimal result, result2;
        result = round(szorzat, 2);
        result2 = round(FGame_default_bet, 2);

        if (!result.equals(result2)) {
            assertFalse(true);
            System.out.println("Hibás a default bet a jatékban!");
            return false;
        }
        return true;
    }

    public void GMT_SetUp_DefaultBet_aboutGDD_and_check_InGame() throws InterruptedException {
        //vissza állítom GMT-ben a GDD-ben szereplő értékre
        String GMT_default_denom = driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[5]/span/span/span[1]")).getText();
        String GMT_default_coin = driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[6]/span/span/span[1]")).getText();
        String GMT_default_line = driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[7]/span/span/span[1]")).getText();
        Thread.sleep(500);

        //denom
        driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[5]/span/span/span[1]")).click();
        Thread.sleep(500);
        List<WebElement> denomi = driver.findElements(By.xpath("//*[@id=\"Denominations_listbox\"]/li"));
        denomi.stream().filter(f -> f.getText().equals(Gdd_default_denom)).collect(Collectors.toList()).get(0).click();
        Thread.sleep(500);
        //coin
        driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[6]/span/span/span[1]")).click();
        Thread.sleep(500);
        List<WebElement> coinn = driver.findElements(By.xpath("//*[@id=\"Coins_listbox\"]/li"));
        coinn.stream().filter(f -> f.getText().equals(Gdd_default_coin)).collect(Collectors.toList()).get(0).click();
        Thread.sleep(500);
        //lines
        driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[7]/span/span/span[1]")).click();
        Thread.sleep(500);
        List<WebElement> liness = driver.findElements(By.xpath("//*[@id=\"Lines_listbox\"]/li"));
        liness.stream().filter(f -> f.getText().equals(Gdd_default_line)).collect(Collectors.toList()).get(0).click();
        Thread.sleep(500);
        //Save
        driver.findElement(By.xpath("//*[@id=\"saveButton\"]")).click();

        if (Game_bet_value_check(Gdd_default_denom, Gdd_default_coin, Gdd_default_line) != true) {
            assertFalse(true);
        } else {
            assertTrue(true);
            driver.quit();
        }
    }

    public String[] GMT_SetUp_DefaultBet_toMinimum_Values() throws InterruptedException {
        String[] array = new String[3];
        //itt beallitom a legkisebb ertekekre
        String get_denom = "";
        String get_coin = "";
        String get_lines = "";

        //denoml
        driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[5]/span/span/span[1]")).click();
        List<WebElement> denom = driver.findElements(By.xpath("//*[@id=\"Denominations_listbox\"]/li"));
        get_denom = denom.stream().filter(f -> !f.getText().equals("")).collect(Collectors.toList()).get(0).getText();  //hasznos SZURES
        denom.stream().filter(f -> !f.getText().equals("")).collect(Collectors.toList()).get(0).click();
        (new WebDriverWait(driver, 100)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[6]/span/span/span[1]")));
        //coin
        driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[6]/span/span/span[1]")).click();
        List<WebElement> coin = driver.findElements(By.xpath("//*[@id=\"Coins_listbox\"]/li"));
        get_coin = coin.stream().filter(f -> !f.getText().equals("")).collect(Collectors.toList()).get(0).getText();
        coin.stream().filter(f -> !f.getText().equals("")).collect(Collectors.toList()).get(0).click();
        (new WebDriverWait(driver, 100)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[7]/span/span/span[1]")));
        //lines
        driver.findElement(By.xpath("//*[@id=\"updateForm\"]/div[1]/div/div[7]/span/span/span[1]")).click();
        List<WebElement> lines = driver.findElements(By.xpath("//*[@id=\"Lines_listbox\"]/li"));
        get_lines = lines.stream().filter(f -> !f.getText().equals("")).collect(Collectors.toList()).get(0).getText();
        lines.stream().filter(f -> !f.getText().equals("")).collect(Collectors.toList()).get(0).click();
        (new WebDriverWait(driver, 100)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"saveButton\"]")));
        //Save
        driver.findElement(By.xpath("//*[@id=\"saveButton\"]")).click();

        array[0] = get_denom;
        array[1] = get_coin;
        array[2] = get_lines;

        return array;
    }

    private void GMTConfigurationHighDenom(String settingsName, ArrayList<Double> array) throws InterruptedException {
        //atmegy a configuration oldalra 
        driver.navigate().to("http://pngqa19.playngonetwork.com:8080/configuration/configuration");
        driver.findElement(By.id("NewBtn")).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[1]/div[1]/span/span/span[2]")).click();

        Thread.sleep(500);

        //rakeres a setting name-re 
        driver.findElement(By.xpath("//*[@id=\'ConfigurationName-list\']/span/input")).sendKeys(settingsName);

        Thread.sleep(500);

        //kivaslasztja es rakattint a product group dropdownra
        driver.findElement(By.xpath("//*[@id=\'Name\']")).click();
        driver.findElement(By.xpath("//*[@id=\"newValueForm\"]/div[1]/div[2]/span/span/span[1]")).click();

        Thread.sleep(500);

        //stubbedot valaszt majd rakattint a Game dropdownra
        driver.findElements(By.xpath("//*[@id=\"AddProductGroup_listbox\"]/li")).get(1).click();
        driver.findElement(By.xpath("//*[@id='newValueForm']/div[2]/div[2]/span/span")).click();

        Thread.sleep(500);

        //beirja a game id-t
        driver.findElement(By.xpath("//*[@id=\"AddGames-list\"]/span/input")).sendKeys(gameID);

        Thread.sleep(500);

        //kivalatsztja a jatekot, hozzaadja a beallitast es elnavigal az oldalra 
        driver.findElements(By.xpath("//*[@id=\"AddGames_listbox\"]/li")).get(0).click();
//        
//     
//        WebElement add = driver.findElement(By.xpath("//*[@id=\"deomination-grid\"]/div[1]/a"));
//        if (!add.getText().equals("Add new record")) {
//
//        } else {
//            add.click();
//            Thread.sleep(500);
//            driver.findElement(By.xpath("//*[@id=\"deomination-grid\"]/div[3]/table/tbody/tr/td[1]")).click();
//            driver.findElement(By.xpath("//*[@id='deomination-grid']/div[3]/table/tbody/tr/td[1]/span/span/input[2]")).sendKeys(fake_denom);
//
//        }
//
//        //hozzaad
//        driver.findElement(By.id("AddBtn")).click();
    }
}
