package com.example.foodapp;

import android.content.ClipData;
import android.content.Context;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Console;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private ArrayList<String[]> recipeTexts = new ArrayList<String[]>();
    //private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    //private RecipeSearch searchBar;
    //private ArrayList<Recipe> resultOfSearch;
    private RecyclerView recyclerView;
    private List<ClipData.Item> itemList;
    private SearchView searchbar;
    private ArrayList<Recipe> recipeList;
    private ListView listView;
    private ListViewAdapter adapter;
    //private ItemAdapter itemAdapter;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecipeFragment() {
        // Required empty public constructor
    }

    //loads in the text for the recipes given a path name
    //needs to improve
    public String[] loadTextFileFromAssets(String pathName){
        String content = null;
        AssetManager manager = getContext().getAssets();
        try {

            InputStream is = manager.open(pathName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            content = new String(buffer,"UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        /* IMPORTANT DO NOT OVERLOOK: this version works "\n" but on Rodrigo's "\r\n" */
        String[] splitContent = content.split("\r\n");
        return splitContent;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //recipeList = new ArrayList<>();
        //Bundle b = this.getArguments();
        //if(b.getSerializable("recipeList") != null)
        //    recipeList = (ArrayList<Recipe>)b.getSerializable("recipeList");

        //System.out.println("Map in fragment: "+recipeList.toString());
        //setContentView(R.layout.fragment_recipe);
        //recipeTexts.add(loadTextFileFromAssets("beefWellington.txt"));
        //recipeTexts.add(loadTextFileFromAssets("vegetableStirFry.txt"));
        //recipeTexts.add(loadTextFileFromAssets("bellPepperKetoNachos.txt"));


        //for(String[] recipe : recipeTexts){
        //    Log.e("recipe", recipe[0]);
         //   recipes.add(new Recipe(recipe));
        //}
        //searchBar = new RecipeSearch(recipes, this.getContext());
        //System.out.println(searchMap);
        //for(String key : searchMap.keySet()){
          //  System.out.println(key);
            //System.out.println(searchMap.get(key).toString());
        //}
        //System.out.println(searchMap.get("beef\r"));


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        //SEARCH CODE


    }





    //FRAGMENT LINK
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_recipe, container, false);
        recipeList = new ArrayList<>();
        Bundle b = this.getArguments();
        if(b.getSerializable("recipeList") != null)
            recipeList = (ArrayList<Recipe>)b.getSerializable("recipeList");

        //System.out.println("Map in fragment: "+recipeList.toString());

        listView = (ListView) fragmentView.findViewById(R.id.listView);
        adapter = new ListViewAdapter(this.getContext(), recipeList);
        listView.setAdapter(adapter);

        searchbar =  (SearchView) fragmentView.findViewById(R.id.searchView);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                System.out.println(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s;
                //System.out.println(text);
                adapter.filter(text);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //this returns the recipe associated with the click Since it will show every recipe associated with the search
                Recipe clickedRecipe = (Recipe) adapterView.getItemAtPosition(i);
                clickedRecipe.print_recipe();
            }
        });



        // Inflate the layout for this fragment
        return fragmentView;
    }


}