package com.censoinegi.service;


import java.sql.Connection;
import java.sql.DriverManager;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.kmftpyuaghnhnvgdwvhj.supabase.co:5432/postgres?sslmode=require";
        String user = "postgres";
        String pass = "MnXuxeBOboghUhz2";

        System.out.println("🔄 Intentando conectar a Supabase...");
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("✅ Conexión exitosa a Supabase!");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión:");
            e.printStackTrace();
        }
    }
}
