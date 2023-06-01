/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;
import java.util.*;
import java.util.stream.Collectors;
/**
 *
 * @author kiki
 */
public class Mavenproject1 {
    static class Country{
        private String name;
        private int population;
        private String continent;
        public Country(int pop, String continent){
            this.population = pop;
            this.continent = continent;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public int getPopulation(){
            return this.population;
        }
        public String getContinent(){
            return this.continent;
        }
    }
    public static int getPopulation(List<Country> countries, String continent){
        int size = countries.size();
        int contPop = 0;
        for(int i = 0; i < size; i++){
            if(countries.get(i).getContinent().equals(continent)){
                contPop += countries.get(i).getPopulation();
            }
        }
        return contPop;
    }
    public static List<Integer> getRandomSubset (List<Integer> list){
        var rand = new Random();
        int randInts = rand.nextInt(list.size());
        List<Integer> subset = list;
        //System.out.println(randInts);
        for(int i = 0; i < randInts; i++){
            int toEliminate = rand.nextInt(list.size());
            //System.out.println(toEliminate);
            subset = subset.stream().filter(n->(list.indexOf(n + 1) != toEliminate)).collect(Collectors.toList());
            //System.out.println(subset);
        }
        return subset;
    }
    public static void main(String[] args) {
        Country england = new Country(55000000, "Europe");
        Country germany = new Country(81000000, "Europe");
        Country poland = new Country(40000000, "Europe");
        Country croatia = new Country(4000000, "Europe");
        Country china = new Country(1400000000, "Asia");
        Country japan = new Country(120000000, "Asia");
        List<Country> countries = new ArrayList<Country>();
        countries.add(england);
        countries.add(germany);
        countries.add(poland);
        countries.add(croatia);
        countries.add(china);
        countries.add(japan);
        //System.out.println(getPopulation(countries, "Asia"));
        List list = Arrays.asList(1,2,3,4,5);
        System.out.println(getRandomSubset(list));
    }
}