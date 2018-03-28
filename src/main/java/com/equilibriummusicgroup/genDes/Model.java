package com.equilibriummusicgroup.genDes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Random;

public class Model {

    private String language;

    private String channel;

    private String inputDescription;

    private String albumURL;


    public Model(String language, String channel, String inputDescription) {
        this.language = language;
        this.channel = channel;
        this.inputDescription = inputDescription;
    }


    public String getResult(String link) throws InterruptedException, CustomException {
//        Thread.sleep(2000);
        String finalDescription = "";
        iTunesApiQueryUtils apiClass = new iTunesApiQueryUtils();

        JsonObject json = apiClass.getJson(link);

        if (json == null) {
            return null;
        }

        this.albumURL = parseJson(json);
        if(albumURL == null){
            throw new CustomException("No Albums associated with that UPC!");
        }

        if (!albumURL.isEmpty()) {

            switch (this.channel){
                case ("MRC"):
                    System.out.println("###MRC###");
                    finalDescription = generateMrcDescription();

                    break;

                case ("Sleep"):
                    System.out.println("###Sleep###");
                    finalDescription = generateSleepDescription();

                    break;

                case("Buddha"):
                    System.out.println("###Buddha###");
                    finalDescription = generateBuddhaDescription();

                    break;
            }

        }
        return finalDescription;
    }

    /**
     * Method that parses the json retrieved from <code>getResult</code> and returns the album URL
     *
     * @param json the json object retrieved from getResult
     * @return String the album URL
     */
    private String parseJson(JsonObject json) throws CustomException {

        String albumURL = "";
        int result;

        if(checkNode(json, "resultCount")){
            result = json.get("resultCount").getAsInt();
        } else {
            throw new CustomException("No resultCount field!");
        }

        System.out.println("APIGUI JSON: " + json.toString());
        System.out.println("RESULTCOUNT: " + result);

        if (result > 0) {

            JsonArray jArray = json.get("results").getAsJsonArray();
            JsonObject jsonObjArr = jArray.get(0).getAsJsonObject();
            albumURL = jsonObjArr.get("collectionViewUrl").getAsString();
        } else {
            return null;
        }

        return albumURL;
    }

    /**
     * Method that generates the description based on <code>this.language</code>
     *
     * @return String with the complete description
     */
    private String generateMrcDescription() {

        String des = "";
        StringBuilder builder;
        Random rand;
        ArrayList<Integer> list;

        switch (this.language) {

            case ("EN"):
                builder = new StringBuilder(constantFields.EN_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.EN_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.EN_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                //I build a list of integers of size this.enKeywords
                list = new ArrayList<Integer>(MrcConstants.enKeywords.size());
                for (int i = 1; i <= MrcConstants.enKeywords.size(); i++) {
                    list.add(i);
                }

                System.out.println("LIST: " + list);

                //While the list that I've just built is greater than 7, I proceed to pick a random int between 0 and list.size()-1 and
                //remove from list the index equals to that randomly picked int.
                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.enKeywords.size(): " + MrcConstants.enKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.enKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                System.out.println("AFTER WHILE LOOP LIST: " + list);

                builder.append(constantFields.EN_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

            case ("ES"):
                builder = new StringBuilder(constantFields.ES_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.ES_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.ES_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.esKeywords.size());
                for (int i = 1; i <= MrcConstants.esKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.esKeywords.size(): " + MrcConstants.esKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.esKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.ES_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("DE"):

                builder = new StringBuilder(constantFields.DE_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.DE_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.DE_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.deKeywords.size());
                for (int i = 1; i <= MrcConstants.deKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.deKeywords.size(): " + MrcConstants.deKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.deKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.DE_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("PT"):
                builder = new StringBuilder(constantFields.PT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.PT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.PT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                list = new ArrayList<Integer>(MrcConstants.ptKeywords.size());
                for (int i = 1; i <= MrcConstants.ptKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.ptKeywords.size(): " + MrcConstants.ptKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.ptKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.PT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("IT"):
                builder = new StringBuilder(constantFields.IT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.IT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.IT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.itKeywords.size());
                for (int i = 1; i <= MrcConstants.itKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.itKeywords.size(): " + MrcConstants.itKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.itKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.IT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

        }

        return des;
    }

    /**
     * Method that generates the description based on <code>this.language</code>
     *
     * @return String with the complete description
     */
    private String generateSleepDescription() {

        String des = "";
        StringBuilder builder;
        Random rand;
        ArrayList<Integer> list;

        switch (this.language) {

            case ("EN"):
                builder = new StringBuilder(constantFields.EN_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.EN_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.EN_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                //I build a list of integers of size this.enKeywords
                list = new ArrayList<Integer>(MrcConstants.enKeywords.size());
                for (int i = 1; i <= MrcConstants.enKeywords.size(); i++) {
                    list.add(i);
                }

                System.out.println("LIST: " + list);

                //While the list that I've just built is greater than 7, I proceed to pick a random int between 0 and list.size()-1 and
                //remove from list the index equals to that randomly picked int.
                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.enKeywords.size(): " + MrcConstants.enKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.enKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                System.out.println("AFTER WHILE LOOP LIST: " + list);

                builder.append(constantFields.EN_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

            case ("ES"):
                builder = new StringBuilder(constantFields.ES_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.ES_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.ES_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.esKeywords.size());
                for (int i = 1; i <= MrcConstants.esKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.esKeywords.size(): " + MrcConstants.esKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.esKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.ES_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("DE"):

                builder = new StringBuilder(constantFields.DE_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.DE_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.DE_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.deKeywords.size());
                for (int i = 1; i <= MrcConstants.deKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.deKeywords.size(): " + MrcConstants.deKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.deKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.DE_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("PT"):
                builder = new StringBuilder(constantFields.PT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.PT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.PT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                list = new ArrayList<Integer>(MrcConstants.ptKeywords.size());
                for (int i = 1; i <= MrcConstants.ptKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.ptKeywords.size(): " + MrcConstants.ptKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.ptKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.PT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("IT"):
                builder = new StringBuilder(constantFields.IT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.IT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.IT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.itKeywords.size());
                for (int i = 1; i <= MrcConstants.itKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.itKeywords.size(): " + MrcConstants.itKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.itKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.IT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

        }

        return des;
    }


    /**
     * Method that generates the description based on <code>this.language</code>
     *
     * @return String with the complete description
     */
    private String generateBuddhaDescription() {

        String des = "";
        StringBuilder builder;
        Random rand;
        ArrayList<Integer> list;

        switch (this.language) {

            case ("EN"):
                builder = new StringBuilder(constantFields.EN_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.EN_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.EN_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                //I build a list of integers of size this.enKeywords
                list = new ArrayList<Integer>(MrcConstants.enKeywords.size());
                for (int i = 1; i <= MrcConstants.enKeywords.size(); i++) {
                    list.add(i);
                }

                System.out.println("LIST: " + list);

                //While the list that I've just built is greater than 7, I proceed to pick a random int between 0 and list.size()-1 and
                //remove from list the index equals to that randomly picked int.
                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.enKeywords.size(): " + MrcConstants.enKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.enKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                System.out.println("AFTER WHILE LOOP LIST: " + list);

                builder.append(constantFields.EN_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

            case ("ES"):
                builder = new StringBuilder(constantFields.ES_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.ES_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.ES_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.esKeywords.size());
                for (int i = 1; i <= MrcConstants.esKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.esKeywords.size(): " + MrcConstants.esKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.esKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.ES_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("DE"):

                builder = new StringBuilder(constantFields.DE_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.DE_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.DE_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.deKeywords.size());
                for (int i = 1; i <= MrcConstants.deKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.deKeywords.size(): " + MrcConstants.deKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.deKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.DE_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("PT"):
                builder = new StringBuilder(constantFields.PT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.PT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.PT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                list = new ArrayList<Integer>(MrcConstants.ptKeywords.size());
                for (int i = 1; i <= MrcConstants.ptKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.ptKeywords.size(): " + MrcConstants.ptKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.ptKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.PT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("IT"):
                builder = new StringBuilder(constantFields.IT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + constantFields.IT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(constantFields.IT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.itKeywords.size());
                for (int i = 1; i <= MrcConstants.itKeywords.size(); i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > 7) {
                    System.out.println("this.itKeywords.size(): " + MrcConstants.itKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size() - 1);
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.itKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(constantFields.IT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

        }

        return des;
    }


    /*private String testGenerateDescription() {

        StringBuilder builder;
        Random rand;
        ArrayList<Integer> list;

        builder = new StringBuilder(constantFields.EN_FIRST_SENTENCE.getField());
        builder.append(this.albumURL);

        builder.append("\n" + constantFields.EN_SECOND_SENTENCE.getField() + "\n\n");

        builder.append(this.inputDescription + "\n\n");

        builder.append(constantFields.EN_TOP_DESCRIPTION.getField());

        builder.append("\n\n");

        list = new ArrayList<Integer>(this.enKeywords.size());
        for (int i = 1; i <= this.enKeywords.size(); i++) {
            list.add(i);
        }

        System.out.println("LIST: " + list);


        rand = new Random();
        while (list.size() > 7) {
            System.out.println("this.enKeywords.size(): " + this.enKeywords.size());
            System.out.println("LIST: " + list);
            int index = rand.nextInt(list.size() - 1);
            System.out.println("INDEX: " + index);
            builder.append(this.enKeywords.get(list.remove(index)));
            builder.append("\n");
        }

        System.out.println("AFTER WHILE LOOP LIST: " + list);

        list = new ArrayList<Integer>(this.enKeywords.size());
        for (int i = 1; i <= this.enKeywords.size(); i++) {
            list.add(i);
        }


        builder.append(constantFields.EN_BOTTOM_DESCRIPTION.getField());

        System.out.println(builder);

        return builder.toString();
    }*/

    /**
     * Helper method to check whether the json retrieved has the field passed as a parameter the <code>jsonResponse</code> from the <code>com.equilibriummusicgroup.AMCharts.model.JsonQueryUtils</code>class.
     *
     * @param gson the json object retrieved
     * @param key  the key against which the check is made
     * @return Boolean
     */
    private Boolean checkNode(JsonObject gson, String key) {
        return gson.has(key);
    }
}
