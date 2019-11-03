import java.util.List;

public class Compare {
    protected int score(Mentor m, Mentee s) {

        int count = 0;


        if (m.getCampus1().equals(s.getCampus1()))
            count++;
        if (m.getCampus2().equals(s.getCampus2()))
            count++;
        if (m.getCampus3().equals(s.getCampus3()))
            count++;

        if (m.getwantOffer().indexOf(",") != -1)//has comma
        {
            String[] indsM = m.getwantOffer().split(",");
            String[] indsS = s.getwantOffer().split(",");
            if (indsM.length <= indsS.length) {
                for (int j = 0; j < indsM.length; j++) {
                    for (int k = 0; k < indsS.length; k++) {
                        if (indsM[j] == indsS[k])
                            count++;
                    }
                }
            } else {
                for (int n = 0; n < indsS.length; n++) {
                    for (int m = 0; m < indsM.length; m++) {
                        if (indsM[n] == indsS[m])
                            count++;
                    }
                }
            }
        }

        if (m.getGain().indexOf(",") != -1)//has comma
        {
            String[] indsM = m.getGain().split(",");
            String[] indsS = s.getGain().split(",");
            if (indsM.length <= indsS.length) {
                for (int j = 0; j < indsM.length; j++) {
                    for (int k = 0; k < indsS.length; k++) {
                        if (indsM[j] == indsS[k])
                            count++;
                    }
                }
            } else {
                for (int n = 0; n < indsS.length; n++) {
                    for (int m = 0; m < indsM.length; m++) {
                        if (indsM[n] == indsS[m])
                            count++;
                    }
                }
            }
        }

        if (m.getInterests().indexOf(",") != -1)//has comma
        {
            String[] indsM = m.getInterests().split(",");
            String[] indsS = s.getInterests().split(",");
            if (indsM.length <= indsS.length) {
                for (int j = 0; j < indsM.length; j++) {
                    for (int k = 0; k < indsS.length; k++) {
                        if (indsM[j] == indsS[k])
                            count++;
                    }
                }
            } else {
                for (int n = 0; n < indsS.length; n++) {
                    for (int m = 0; m < indsM.length; m++) {
                        if (indsM[n] == indsS[m])
                            count++;
                    }
                }
            }
        }

        if (m.getPriorities().indexOf(",") != -1)//has comma
        {
            String[] indsM = m.getPriorities().split(",");
            String[] indsS = s.getPriorities().split(",");
            if (indsM.length <= indsS.length) {
                for (int j = 0; j < indsM.length; j++) {
                    for (int k = 0; k < indsS.length; k++) {
                        if (indsM[j] == indsS[k])
                            count++;
                    }
                }
            } else {
                for (int n = 0; n < indsS.length; n++) {
                    for (int m = 0; m < indsM.length; m++) {
                        if (indsM[n] == indsS[m])
                            count++;
                    }
                }
            }
        }


        return count;
    }

    public static void main(String[] args)
    {
        public static void main(String[] args){
        List<Object> mentor = MentorSheets.getMentor();
        int[] scores = new int[mentor.size()];
        //int size = mentor.size();
        for(int a  = 0; a < size; a++)
        {
            for(int b = 0; b < scores.length; b++)
            {
                scores[a] = score(mentor.get(a), mentee.get(a));
            }
            int index = 0;
            for(int i = 0; i < scores.length; i++){
                int max = Integer.MIN_VALUE;

                if(scores[i] > max) {
                    max = scores[i];
                    index = i;

                }
            }
            System.out.println(mentee.get(a) + " with Mentor " + mentor.get(index));
        }
    }
    }
}
