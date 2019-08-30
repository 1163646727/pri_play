/**
 * className: PriThreadExtThreatTest <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date:
 * 2019/8/23 16:06 <BR> version 1.0 jdk1.8 <BR>
 */
public class PriThreadExtThreatTest {

    public static void main(String[] args) {
        PriThreadExtThreat priThreadExtThreat = new PriThreadExtThreat();
        PriThreadExtThreat priThreadExtThreat2 = new PriThreadExtThreat();
        PriThreadExtThreat priThreadExtThreat3 = new PriThreadExtThreat();
        priThreadExtThreat.start();
        priThreadExtThreat2.start();
        priThreadExtThreat3.start();
    }
}
