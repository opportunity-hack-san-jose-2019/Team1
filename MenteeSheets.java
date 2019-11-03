import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MenteeSheets {
    public static List<Object> getMentee() {
        return mentee;
    }

    public static void setMentee(List<Object> mentee) {
        MenteeSheets.mentee = mentee;
    }

    public static void setSheetsService(Sheets sheetsService) {
        MenteeSheets.sheetsService = sheetsService;
    }

    public static String getApplicationName() {
        return APPLICATION_NAME;
    }

    public static void setApplicationName(String applicationName) {
        APPLICATION_NAME = applicationName;
    }

    public static String getSpreadsheetId() {
        return SPREADSHEET_ID;
    }

    public static void setSpreadsheetId(String spreadsheetId) {
        SPREADSHEET_ID = spreadsheetId;
    }

    private static List<Object> mentee = new ArrayList<Object>();
    private static Sheets sheetsService;
    private static String APPLICATION_NAME = "Sheets";
    private static String SPREADSHEET_ID = "1f_iweCLX3tSWA_X3vBSrdE4Q7kXxDUoCnIeMpdoOzq8";

    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = MenteeSheets.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
                .authorize("user");

        return credential;

    }

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }










    public static void main(String[] args) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        String range = "mentor";

        ValueRange response = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();

        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            System.out.println("No data found");
        } else {
            for (List row : values) {
                mentee.add(new Mentee((String)row.get(1), (String)row.get(9), (String)row.get(16), (String)row.get(12),
                        (String)row.get(13), (String)row.get(16), (String)row.get(15), (String)row.get(18),
                        (String)row.get(19), (String)row.get(20)));
                System.out.println(row);
                //System.out.printf("%s %s from %s\n", row.get(0), row.get(1), row.get(2));
            }
        }
    }

    static class Mentee {

        private String name; //? 1 for both
        private String wantOffer; //? 9 for mentee, ? 12 for mentor
        private String experience; //? 16 (only if "previous experience" is checked), ? 2 or ? 10 need to be true for mentor
        private String frequency; //? 12 for mentee, ? 13 for mentor
        private String interests; //?13 for mentee, ? 16 for mentor
        private String priorityPersona; //? 16 for mentee, ? 15 for mentor
        private String gain; //? 15 for mentee, ? 14 for mentor
        private String campus1; //18 - 20 for mentee, ? 21 - 23 for mentor
        private String campus2;
        private String campus3;

        public Mentee(String name, String want, String experience, String frequency, String interests, String gain, String priority,
                      String campus1, String campus2, String campus3) {
            this.name = name;
            this.wantOffer = want;
            this.experience = experience;
            this.frequency = frequency;
            this.interests = interests;
            this.gain = gain;
            this.priorityPersona = priority;
            this.campus1 = campus1;
            this.campus2 = campus2;
            this.campus3 = campus3;
        };

        public String getName() {
            return name;
        }

        public String getWantOffer() {
            return wantOffer;
        }

        public String getExperience() {
            return experience;
        }

        public String getFrequency() {
            return frequency;
        }

        public String getInterests() {
            return interests;
        }

        public String getPriorityPersona() {
            return priorityPersona;
        }

        public String getGain() {
            return gain;
        }

        public String getCampus1() {
            return campus1;
        }

        public String getCampus2() {
            return campus2;
        }

        public String getCampus3() {
            return campus3;
        }
    }
}