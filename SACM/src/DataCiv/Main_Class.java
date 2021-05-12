/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCiv;

import java.io.*;
import java.util.*;

/**
 *
 * @author evan
 */
public class Main_Class {
    public static void main(String[]args)throws Exception{
        data.intMahasiswa();
        data.intDosen();
        data.addMahasiswa("AKU","123123","TIF","FILKOM");
        data.addMahasiswa("DIA","123456","TIF","FILKOM");
        data.addDosen("ANDA","0987654","TIF","FILKOM");
        data.addDosen("KAMU","5632724","TIF","FILKOM");
        data.addDosen("MEREKA", "694200","TIF","FILKOM");
        data.printMahasiswa();
        data.printDosen();
        data.hapusMahasiswa("Aku", "123123","TIF","FILKOM");
        data.hapusDosen("MerEkA", "694200","TIF","FILKOM");
        data.printMahasiswa();
        data.printDosen();
    }
}

class data {
    static ArrayList<String> civ=new ArrayList<>();
    static void intMahasiswa() throws Exception {
        init("mahasiswa.txt");
    }
    static void addMahasiswa(String nama,String nim, String prodi, String fakultas) throws Exception{
        adder("mahasiswa.txt",nama,nim);
    }
    static void printMahasiswa()throws Exception{
        printer("mahasiswa.txt");
    }
    static void hapusMahasiswa(String nama, String nim, String prodi, String fakultas) throws Exception{
        delete("mahasiswa.txt",nama,nim);
    }
        static void intDosen() throws Exception {
        init("dosen.txt");
    }
    static void addDosen(String nama,String nip, String prodi, String fakultas) throws Exception{
        adder("dosen.txt",nama,nip);
    }
    static void printDosen()throws Exception{
        printer("dosen.txt");
    }
    static void hapusDosen(String nama,String nip, String prodi, String fakultas)throws Exception{
        delete("dosen.txt",nama,nip);
    }
    private static void init(String fileString) throws Exception {
        FileReader read;
        try{
            read=new FileReader(fileString);
        }catch(FileNotFoundException e){
            File file=new File(fileString);
            file.createNewFile();
            read=new FileReader(file);
        }
    }
    private static void adder(String file, String nama,String nomor) throws Exception{
        FileReader read=new FileReader(file);
        boolean ada=ada(read,nama,nomor);
        if(ada==false){
            Writer add;
            add=new FileWriter(file,true);
            add.append(nama + "-" + nomor + "\n");
            add.close();
        }
    }
    private static boolean ada(FileReader file,String nama, String nomor) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(file);
        boolean a=false;
        String lines;
        while ((lines=bufferedReader.readLine()) != null) {
            String line[]=lines.trim().split("-");
            if(line[0].equals(nama) && line[1].equals(nomor))return a=true;
        }
        return a;
    }
    private static void printer(String namaFile)throws Exception{
        FileReader read;
        ArrayList<String> readingLines=new ArrayList<>();
        String line;
        try{
            read=new FileReader(namaFile);
            BufferedReader bufferedReader = new BufferedReader(read);
            while ((line = bufferedReader.readLine()) != null) {
                readingLines.add(line);
            }
            bufferedReader.close();
            for (int i = 0; i < readingLines.size(); i++) {
                System.out.println(readingLines.get(i));
            }
        }catch(FileNotFoundException e){
            
        }
    }
    private static void delete(String namaFile,String nama, String nomor)throws Exception{
        init("tmp"+namaFile);
        FileReader read;
        ArrayList<String> readingLines=new ArrayList<>();
        String line;

        read=new FileReader(namaFile);
        BufferedReader bufferedReader = new BufferedReader(read);
        while ((line = bufferedReader.readLine()) != null) {
            readingLines.add(line);
        }
        bufferedReader.close();
        for (int i = 0; i < readingLines.size(); i++) {
            String []tmp=readingLines.get(i).trim().split("-");
            if(tmp[0].equalsIgnoreCase(nama) && tmp[1].equalsIgnoreCase(nomor));
            else adder("tmp"+namaFile,tmp[0],tmp[1]);
        }

        BufferedWriter out = new BufferedWriter (new FileWriter(namaFile));
        out.write("aString1\n");out.close();
        boolean success = (new File(namaFile)).delete();
        
        init(namaFile);
        FileReader read2;
        ArrayList<String> readingLines2=new ArrayList<>();
        String line2;

        read2=new FileReader("tmp"+namaFile);
        BufferedReader bufferedReader2 = new BufferedReader(read2);
        while ((line = bufferedReader2.readLine()) != null) {
            readingLines2.add(line);
        }
        bufferedReader2.close();
        for (int i = 0; i < readingLines2.size(); i++) {
            String []tmp=readingLines2.get(i).trim().split("-");
            adder(namaFile,tmp[0],tmp[1]);
        }
        
        BufferedWriter out2 = new BufferedWriter (new FileWriter("tmp"+namaFile));
        out2.write("aString1\n");out2.close();
        boolean success2 = (new File("tmp"+namaFile)).delete();
    }
    static void addCiv(String nama, String prodi, String fakultas){
        civ.add(nama+"-"+prodi+"-"+fakultas);
    }
}




