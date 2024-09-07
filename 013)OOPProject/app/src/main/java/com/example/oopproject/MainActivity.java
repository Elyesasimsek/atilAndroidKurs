package com.example.oopproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       /* User myuser = new User();
        myuser.name = "Elyesa";
        myuser.job = "Software Engineeiring";*/
        User myUser = new User("Elyesa", "Software Engineeiring");
        System.out.println(myUser.information());
        System.out.println(myUser.name);

        //Encapsulation
        Family elyesa = new Family("Elyesa", 25);
        System.out.println(elyesa.getName());
        elyesa.setAge(26, "Elyesa");
        System.out.println(elyesa.getAge());

        //Inheritance
        SuperFamily fatma = new SuperFamily("Fatma", 25);
        System.out.println(fatma.sing());
        System.out.println(fatma.getAge());

        //Polymorphism
        //Statik Polymorphism
        Mathematics mathematics = new Mathematics();
        System.out.println(mathematics.sum());
        System.out.println(mathematics.sum(5, 3));
        System.out.println(mathematics.sum(5, 3, 4));

        //Dynamic Polymorphism
        Animal myAnimal = new Animal();
        myAnimal.sing();

        Dog barley = new Dog();
        barley.test();
        barley.sing();

        Piano myPiano = new Piano();
        myPiano.brand = "Yamaha";
        myPiano.digital = true;
        myPiano.info();

    }
}