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

        /*JsonObject json = apiClass.getJson(link);

        if (json == null) {
            return null;
        }

        this.albumURL = parseJson(json);
        if(albumURL == null){
            throw new CustomException("No Albums associated with that UPC!");
        }

        this.albumURL = link;*/

        JsonObject json = null;

        if (true) {

            switch (this.channel){
                case ("MRC"):
                    System.out.println("###MRC###");
                    finalDescription = generateMrcDescription();

                    break;

                case ("Sleep"):
                    json = apiClass.getJson(link);

                    if (json == null) {
                        return null;
                    }

                    this.albumURL = parseJson(json);
                    if(albumURL == null){
                        throw new CustomException("No Albums associated with that UPC!");
                    }

                    System.out.println("###Sleep###");
                    finalDescription = generateSleepDescription();

                    break;

                case("Buddha"):
                    json = apiClass.getJson(link);

                    if (json == null) {
                        return null;
                    }

                    this.albumURL = parseJson(json);
                    if(albumURL == null){
                        throw new CustomException("No Albums associated with that UPC!");
                    }

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
                builder = new StringBuilder();
                //builder = new StringBuilder(MrcConstants.enConstantFields.EN_FIRST_SENTENCE.getField());
                //builder.append(this.albumURL);

                //builder.append("\n" + MrcConstants.enConstantFields.EN_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n");

                //builder.append(MrcConstants.enConstantFields.EN_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                System.out.println("MrcConstants.enKeywords.size() " + MrcConstants.enKeywords.size());

                // Build a list of integers of size this.enKeywords
                list = new ArrayList<Integer>(MrcConstants.enKeywords.size());
                for (int i = 0; i <= MrcConstants.enKeywords.size()-1; i++) {
                    list.add(i);
                }

                System.out.println("LIST: " + list);

                //While the list that I've just built is greater than 7, I proceed to pick a random int between 0 and list.size()-1 and
                //remove from list the index equals to that randomly picked int.
                rand = new Random();
                while (list.size() > MrcConstants.enKeywords.size()-3) {
                    System.out.println("MrcConstants.enKeywords.size(): " + MrcConstants.enKeywords.size());
                    System.out.println("LIST: " + list);
                    System.out.println("LIST SIZE: " + list.size());
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.enKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                System.out.println("AFTER WHILE LOOP LIST: " + list);

                builder.append(MrcConstants.enConstantFields.EN_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

            case ("ES"):
                builder = new StringBuilder();
                //builder = new StringBuilder(MrcConstants.esConstantFields.ES_FIRST_SENTENCE.getField());
                //builder.append(this.albumURL);

                //builder.append("\n" + MrcConstants.esConstantFields.ES_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                //builder.append(MrcConstants.esConstantFields.ES_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.esKeywords.size());
                for (int i = 0; i <= MrcConstants.esKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > MrcConstants.esKeywords.size()-3) {
                    System.out.println("this.esKeywords.size(): " + MrcConstants.esKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.esKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(MrcConstants.esConstantFields.ES_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("DE"):
                builder = new StringBuilder();

               // builder = new StringBuilder(MrcConstants.deConstantFields.DE_FIRST_SENTENCE.getField());
               // builder.append(this.albumURL);

               // builder.append("\n" + MrcConstants.deConstantFields.DE_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

               // builder.append(MrcConstants.deConstantFields.DE_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.deKeywords.size());
                for (int i = 0; i <= MrcConstants.deKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > MrcConstants.deKeywords.size()-3) {
                    System.out.println("this.deKeywords.size(): " + MrcConstants.deKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.deKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(MrcConstants.deConstantFields.DE_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("PT"):
                builder = new StringBuilder();
               // builder = new StringBuilder(MrcConstants.ptConstantFields.PT_FIRST_SENTENCE.getField());
               // builder.append(this.albumURL);

                //builder.append("\n" + MrcConstants.ptConstantFields.PT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                //builder.append(MrcConstants.ptConstantFields.PT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                list = new ArrayList<Integer>(MrcConstants.ptKeywords.size());
                for (int i = 0; i <= MrcConstants.ptKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > MrcConstants.ptKeywords.size()-3) {
                    System.out.println("this.ptKeywords.size(): " + MrcConstants.ptKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.ptKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(MrcConstants.ptConstantFields.PT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("IT"):
                builder = new StringBuilder();
               // builder = new StringBuilder(MrcConstants.itConstantFields.IT_FIRST_SENTENCE.getField());
               // builder.append(this.albumURL);

               // builder.append("\n" + MrcConstants.itConstantFields.IT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                //builder.append(MrcConstants.itConstantFields.IT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.itKeywords.size());
                for (int i = 0; i <= MrcConstants.itKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > MrcConstants.itKeywords.size()-3) {
                    System.out.println("this.itKeywords.size(): " + MrcConstants.itKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.itKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(MrcConstants.itConstantFields.IT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

            case ("FR"):
                builder = new StringBuilder();
               // builder = new StringBuilder(MrcConstants.frConstantFields.FR_FIRST_SENTENCE.getField());
                //builder.append(this.albumURL);

               // builder.append("\n" + MrcConstants.frConstantFields.FR_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                //builder.append(MrcConstants.frConstantFields.FR_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(MrcConstants.frKeywords.size());
                for (int i = 0; i <= MrcConstants.frKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > MrcConstants.frKeywords.size()-3) {
                    System.out.println("this.frKeywords.size(): " + MrcConstants.frKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(MrcConstants.frKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(MrcConstants.frConstantFields.FR_BOTTOM_DESCRIPTION.getField());

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
                builder = new StringBuilder(SleepConstants.enConstantFields.EN_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + SleepConstants.enConstantFields.EN_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(SleepConstants.enConstantFields.EN_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                //I build a list of integers of size this.enKeywords
                list = new ArrayList<Integer>(SleepConstants.enKeywords.size());
                for (int i = 0; i <= SleepConstants.enKeywords.size()-1; i++) {
                    list.add(i);
                }

                System.out.println("LIST: " + list);

                //While the list that I've just built is greater than 7, I proceed to pick a random int between 0 and list.size()-1 and
                //remove from list the index equals to that randomly picked int.
                rand = new Random();
                while (list.size() > SleepConstants.enKeywords.size()-3) {
                    System.out.println("this.SleepConstants.size(): " + SleepConstants.enKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(SleepConstants.enKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                System.out.println("AFTER WHILE LOOP LIST: " + list);

                builder.append(SleepConstants.enConstantFields.EN_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

            case ("ES"):
                builder = new StringBuilder(SleepConstants.esConstantFields.ES_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + SleepConstants.esConstantFields.ES_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(SleepConstants.esConstantFields.ES_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(SleepConstants.esKeywords.size());
                for (int i = 0; i <= SleepConstants.esKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > SleepConstants.esKeywords.size()-3) {
                    System.out.println("this.esKeywords.size(): " + SleepConstants.esKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(SleepConstants.esKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(SleepConstants.esConstantFields.ES_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("DE"):

                builder = new StringBuilder(SleepConstants.deConstantFields.DE_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + SleepConstants.deConstantFields.DE_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(SleepConstants.deConstantFields.DE_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(SleepConstants.deKeywords.size());
                for (int i = 0; i <= SleepConstants.deKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > SleepConstants.deKeywords.size()-3) {
                    System.out.println("this.deKeywords.size(): " + SleepConstants.deKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(SleepConstants.deKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(SleepConstants.deConstantFields.DE_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("PT"):
                builder = new StringBuilder(SleepConstants.ptConstantFields.PT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + SleepConstants.ptConstantFields.PT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(SleepConstants.ptConstantFields.PT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                list = new ArrayList<Integer>(SleepConstants.ptKeywords.size());
                for (int i = 0; i <= SleepConstants.ptKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > SleepConstants.ptKeywords.size()-3) {
                    System.out.println("this.ptKeywords.size(): " + SleepConstants.ptKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(SleepConstants.ptKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(SleepConstants.ptConstantFields.PT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("IT"):
                builder = new StringBuilder(SleepConstants.itConstantFields.IT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + SleepConstants.itConstantFields.IT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(SleepConstants.itConstantFields.IT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(SleepConstants.itKeywords.size());
                for (int i = 0; i <= SleepConstants.itKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > SleepConstants.itKeywords.size()-3) {
                    System.out.println("this.itKeywords.size(): " + SleepConstants.itKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(SleepConstants.itKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(SleepConstants.itConstantFields.IT_BOTTOM_DESCRIPTION.getField());

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
                builder = new StringBuilder(BuddhaConstants.enConstantFields.EN_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + BuddhaConstants.enConstantFields.EN_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(BuddhaConstants.enConstantFields.EN_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                //I build a list of integers of size this.enKeywords
                list = new ArrayList<Integer>(BuddhaConstants.enKeywords.size());
                for (int i = 0; i <= BuddhaConstants.enKeywords.size()-1; i++) {
                    list.add(i);
                }

                System.out.println("LIST: " + list);

                //While the list that I've just built is greater than 7, I proceed to pick a random int between 0 and list.size()-1 and
                //remove from list the index equals to that randomly picked int.
                rand = new Random();
                while (list.size() > BuddhaConstants.enKeywords.size()-3) {
                    System.out.println("this.enKeywords.size(): " + BuddhaConstants.enKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(BuddhaConstants.enKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                System.out.println("AFTER WHILE LOOP LIST: " + list);

                builder.append(BuddhaConstants.enConstantFields.EN_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();
                break;

            case ("ES"):
                builder = new StringBuilder(BuddhaConstants.esConstantFields.ES_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + BuddhaConstants.esConstantFields.ES_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(BuddhaConstants.esConstantFields.ES_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(BuddhaConstants.esKeywords.size());
                for (int i = 0; i <= BuddhaConstants.esKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > BuddhaConstants.esKeywords.size() - 3) {
                    System.out.println("this.esKeywords.size(): " + BuddhaConstants.esKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(BuddhaConstants.esKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(BuddhaConstants.esConstantFields.ES_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("DE"):

                builder = new StringBuilder(BuddhaConstants.deConstantFields.DE_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + BuddhaConstants.deConstantFields.DE_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(BuddhaConstants.deConstantFields.DE_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(BuddhaConstants.deKeywords.size());
                for (int i = 0; i <= BuddhaConstants.deKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > BuddhaConstants.deKeywords.size()-3) {
                    System.out.println("this.deKeywords.size(): " + BuddhaConstants.deKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(BuddhaConstants.deKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(BuddhaConstants.deConstantFields.DE_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("PT"):
                builder = new StringBuilder(BuddhaConstants.ptConstantFields.PT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + BuddhaConstants.ptConstantFields.PT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(BuddhaConstants.ptConstantFields.PT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");

                list = new ArrayList<Integer>(BuddhaConstants.ptKeywords.size());
                for (int i = 0; i <= BuddhaConstants.ptKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > BuddhaConstants.ptKeywords.size()-3) {
                    System.out.println("this.ptKeywords.size(): " + BuddhaConstants.ptKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(BuddhaConstants.ptKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(BuddhaConstants.ptConstantFields.PT_BOTTOM_DESCRIPTION.getField());

                System.out.println(builder);

                des = builder.toString();

                break;

            case ("IT"):
                builder = new StringBuilder(BuddhaConstants.itConstantFields.IT_FIRST_SENTENCE.getField());
                builder.append(this.albumURL);

                builder.append("\n" + BuddhaConstants.itConstantFields.IT_SECOND_SENTENCE.getField() + "\n\n");

                builder.append(this.inputDescription + "\n\n");

                builder.append(BuddhaConstants.itConstantFields.IT_TOP_DESCRIPTION.getField());

                builder.append("\n\n");


                list = new ArrayList<Integer>(BuddhaConstants.itKeywords.size());
                for (int i = 0; i <= BuddhaConstants.itKeywords.size()-1; i++) {
                    list.add(i);
                }

                rand = new Random();
                while (list.size() > BuddhaConstants.itKeywords.size()-3) {
                    System.out.println("this.itKeywords.size(): " + BuddhaConstants.itKeywords.size());
                    System.out.println("LIST: " + list);
                    int index = rand.nextInt(list.size());
                    System.out.println("INDEX: " + index);
                    builder.append(BuddhaConstants.itKeywords.get(list.remove(index)));
                    builder.append("\n");
                }

                builder.append("\n\n");

                builder.append(BuddhaConstants.itConstantFields.IT_BOTTOM_DESCRIPTION.getField());

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
