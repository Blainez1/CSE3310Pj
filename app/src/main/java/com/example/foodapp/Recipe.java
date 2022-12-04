package com.example.foodapp;
import java.util.ArrayList;
import android.util.Log;
import java.util.Scanner;
import java.io.File;

public class Recipe{
    public String recipeName;
    public String mainIngredient;
    public String secondaryIngredient;
    public String cookingTime;
    public String servings;
    public float caloricValue;
    public String mealTime;
    public ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
    public ArrayList<String> instructions = new ArrayList<String>();
    private String[] recipeText;

    private String imagePath;


    public Recipe(String[] recipeText){
        this.recipeText = recipeText;
        //this.imagePath = imagePath;
        read_recipe();
    }

    private void read_recipe(){
        //Scanner scan;
        //ArrayList<String> lines = new ArrayList<String>();
        //try{
        //    scan = new Scanner(fileName);
        //}
        //catch(Exception e){
          //  System.out.println("File not found... exiting");
            //return;
        //}
        //while (scan.hasNextLine()){
          //  lines.add(scan.nextLine());
        //}
        //for (String l : lines){
          //  System.out.println("I am line: " + l);
            //System.out.println(lines.size());
        //}
        Log.e("inside recipe", this.recipeText[0]);
        this.recipeName = this.recipeText[0];
        this.mainIngredient = this.recipeText[1];
        this.secondaryIngredient = this.recipeText[2];
        this.cookingTime = this.recipeText[3];
        this.servings = this.recipeText[4];
        this.caloricValue = Float.parseFloat(this.recipeText[5]);
        this.mealTime = this.recipeText[6];
        String[] ingredients = this.recipeText[7].split(",");
        for(String ingredient : ingredients){
            String[] parsed = ingredient.split(" ");
            ingredientList.add(new Ingredient(parsed[0], parsed[1], parsed[3], Float.parseFloat(parsed[2])));
        }
        for(int i = 8; i < this.recipeText.length; i++){
            instructions.add(this.recipeText[i]);
        }
        //scan.close();
    }
    public void print_recipe(){
        System.out.println("TITLE: " + this.recipeName);
        System.out.println("MAIN INGREDIENT: " + this.mainIngredient);
        System.out.println("SECONDARY INGREDIENT: " + this.secondaryIngredient);
        System.out.println("COOKING TIME: " + this.cookingTime + " Hours");
        System.out.println("Servings: " + this.servings);
        System.out.println("CALORIC VALUE PER SERVING: " + this.caloricValue);
        System.out.println("MEAL TIMES: " + mealTime);
        System.out.println("INGREDIENTS:");
        for (Ingredient i : this.ingredientList){
            System.out.print(i.name);
            System.out.print(i.amount.amount);
            System.out.println(i.amount.type);
        }
        for(String l : instructions){
            System.out.println(l);
        }
    }
    public void add_ingredient(Ingredient i){
        ingredientList.add(i);
    }
    public void add_instruction(String line){
        instructions.add(line);
    }


    //public String get_image_path(){
      //  return this.imagePath;
    //}
}
