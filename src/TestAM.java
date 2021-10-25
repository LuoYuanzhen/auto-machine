import TuringMachine.TuringMachine;
import org.junit.Test;

public class TestAM {

    @Test
    public void testTuringMachine(){
        TuringMachine tm = new TuringMachine();
        assert tm.matchString(TestUtils.generateTMLanguage(3, 4, 7, '*')) == true;
        assert tm.matchString(TestUtils.generateTMLanguage(3, 3, 7, '*')) == false;
        assert tm.matchString(TestUtils.generateTMLanguage(4, 4, 6, '*')) == false;
    }
}
