package com.priyanka.javajokerlib;

import java.util.Random;

public class Joker {

    public static String[] jokes= {"A man asks a farmer near a field, “Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.”\n" +
            "\n" +
            "The farmer says, “Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.",
            "What is the difference between a snowman and a snowwoman?\n" +
                    "-\n" +
                    "Snowballs.",
            "I asked my daughter if she’d seen my newspaper. She told me that newspapers are old school. She said that people use tablets nowadays and handed me her iPad. The fly didn’t stand a chance.",
            "Anton, do you think I’m a bad mother?\n" +
                    "\n" +
                    "My name is Paul.",
            "Optimist: The glass is half full.\n" +
                    "\n" +
                    "Pessimist: The glass is half empty.\n" +
                    "\n" +
                    "Mother: Why didn’t you use a coaster!",
            "How many gorillas can fit into a car?\n" +
                    "\n" +
                    "Eight.\n" +
                    "\n" +
                    "How many chickens can fit into the car?\n" +
                    "\n" +
                    "None, the car is already full of gorillas.",
            "\"Mom, where do tampons go?\"\n" +
                    "\n" +
                    "\"Where the babies come from, darling.\"\n" +
                    "\n" +
                    "\"In the stork?",
            "Mother: \"How was school today, Patrick?\"\n" +
                    "\n" +
                    "Patrick: \"It was really great mum! Today we made explosives!\"\n" +
                    "\n" +
                    "Mother: \"Ooh, they do very fancy stuff with you these days. And what will you do at school tomorrow?\"\n" +
                    "\n" +
                    "Patrick: \"What school?"};

    public String tellJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }

}

