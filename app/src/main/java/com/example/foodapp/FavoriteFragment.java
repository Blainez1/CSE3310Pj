package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Recipe> recipeList;
    public Button textButton;
    public Button scanButton;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    public String[] parse_ingredient_list(String ingredients){
        String[] ingredientList;
        if(ingredients.indexOf(", ") != -1){
            ingredientList = ingredients.split(", ");
        }
        else {
            ingredientList = ingredients.split(" ");
        }

        for(int i = 0; i < ingredientList.length; i++){
            if(ingredientList[i].contains("-")){
                int index = ingredientList[i].indexOf("-");
                char[] ingredientsCharArray = ingredientList[i].toCharArray();
                ingredientsCharArray[index] = ' ';
                String newName = String.valueOf(ingredientsCharArray);
                ingredientList[i] = newName;
            }

            ingredientList[i] = ingredientList[i].toLowerCase();
        }


        return  ingredientList;
    }

    public int sum(ArrayList<Integer> numList){
        int sum = 0;
        for (int i : numList){
            sum += i;
        }
        return sum;
    }

    public Recipe classify_ingredients(String ingredients, ArrayList<Recipe> recipeList){
        String[] ingredientList = parse_ingredient_list(ingredients);

        Recipe result;
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (Recipe recipe : recipeList){
            ArrayList<Integer> numList = new ArrayList<Integer>();
            for (String ingredient : ingredientList){
                for(Ingredient ing : recipe.ingredientList){
                    if(ingredient.equals(ing.name)){
                        numList.add(1);
                    }
                    else{
                        numList.add(0);
                    }
                }
            }
            scores.add(sum(numList));
        }

        int max = Collections.max(scores);
        int argmax = scores.indexOf(max);

        result = recipeList.get(argmax);

        return result;
    }

    public ArrayList<Ingredient> missing_ingredients(Recipe result, String[] ingredientList){
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();
        missingIngredients.addAll(result.ingredientList);
        ArrayList<Ingredient> haveIngredients = new ArrayList<>();

        for(String ingredient : ingredientList){
            for(Ingredient ing : result.ingredientList){
                if(ingredient.equals(ing.name)){
                    haveIngredients.add(ing);
                    break;
                }
            }
        }
        for(Ingredient ing : haveIngredients){
            missingIngredients.remove(ing);
        }

        return missingIngredients;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_favorite, container, false);
        recipeList = new ArrayList<>();
        Bundle b = this.getArguments();
        if(b.getSerializable("recipeList") != null)
            recipeList = (ArrayList<Recipe>)b.getSerializable("recipeList");
        // Inflate the layout for this fragment
        System.out.println("Map in fragment: "+recipeList.toString());
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        textButton = (Button) view.findViewById(R.id.textButton);
        scanButton = (Button) view.findViewById(R.id.scanButton);

        //open text prompt for the text button
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIngredientPrompt();
            }
        });
    }
    public void openIngredientPrompt(){
        Intent intent = new Intent(getActivity(), textPromptActivity.class);
        startActivity(intent);
    }
}