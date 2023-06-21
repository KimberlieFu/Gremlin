package gremlins;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

import processing.data.JSONArray;
import processing.data.JSONObject;
import java.util.*;

/**
 * JSONFileReader Class:
 * Reads in JSON files in general
 */
public class JSONFileReader { 

    /**
     * Reads in JSON files
     */
    public static String getJSONFromFile (String filename) {

        // contents of the file
        String jsonText = "";

        try {
            // reads in the json file 
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line;
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File can not be found");
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    /**
     * Get level information from the JSON file
     * @param jsonText The JSON file read in 
     * @return Map of level information
     * @throws CustomExceptions.KeyNotFoundInJSONFileException Exception of there are errors in reading
     */
    public static Map <String, ArrayList> getLevelsFromJSONFile (String jsonText) throws CustomExceptions.KeyNotFoundInJSONFileException {

        // gets the values of the game from JSON file
        Map <String, ArrayList> result = new HashMap <String, ArrayList>();
        ArrayList <String> levels = new ArrayList <String>();
        ArrayList <Double> wizardCDs = new ArrayList <Double>();
        ArrayList <Double> enemyCDs = new ArrayList <Double>();

        // convert to JSON object
        JSONObject jsonObj = JSONObject.parse(jsonText);
        JSONArray levelsJSONArray = jsonObj.getJSONArray("levels");

        // reading the list of level object
        for (int i = 0; i < levelsJSONArray.size(); i ++) {
            JSONObject levelsObj = levelsJSONArray.getJSONObject(i);
            String level = levelsObj.getString("layout");
            Double wizardCD = levelsObj.getDouble("wizard_cooldown");
            Double enemyCD = levelsObj.getDouble("enemy_cooldown");

            if (level == null || wizardCD == null || enemyCD == null) {
                throw new CustomExceptions.KeyNotFoundInJSONFileException ("Key is not found in JSON file");
            }

            levels.add(level);
            wizardCDs.add(wizardCD);
            enemyCDs.add(enemyCD);
        }
      
        result.put("level", levels);
        result.put("wizard_cooldown", wizardCDs);
        result.put("enemy_cooldown", enemyCDs);

        return result;
    }

    /**
     * Get number of lives for wizard
     * @param jsonText The JSON file read in 
     * @return Wizard's lives
     */
    public static int getLivesFromJSONFile (String jsonText) {
        JSONObject jsonObj = JSONObject.parse(jsonText);
        int lives = jsonObj.getInt("lives");

        return lives;
    }
}
