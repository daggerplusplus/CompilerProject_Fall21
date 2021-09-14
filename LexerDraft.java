
import java.util.*;
import java.nio.file.*;
import java.io.*;

//    private Scanner reader;
//    private final String codePath; //pathway of the file containing the Quick Basic code
//    private String basicCode = "";
//    private int position;
//    private String currentString;
//    private static List<String> inputList;
//    private String previousCharacter;
//    private List<String> listOfVariables;
//    private FileWriter Output;
//    private static parserFile parserFile;
//    private String holdNextLine;
//    protected static List<String> lineNumbers;
//    private int tempCount;
//    private String hold;
//    private int posHold;
//    private String currChar;
//    public static ArrayList<String> tokenInfo = new ArrayList<>();

public class LexerDraft {

    private final String codePath; // pathway of the file containing the input java code
    private Scanner reader;
    private String input = "";
    private int position;
    private String currentString = "";
    private static List<String> inputList;
    private static List<String> temp;
    private int lNum;
    // private String previousCharacter;
    // private List<String> listOfVariables;
    private FileWriter Output;
    // private static parserFile parserFile;
    private String holdNextLine;
    protected static List<String> lineNumbers;

    private int tempCount;
    private String hold;
    // private int posHold;
    // private String currChar;
    public static ArrayList<String> tokenInfo = new ArrayList<>();
    public static List<String> listOfComments = new ArrayList<>();

    LexerDraft() {
        codePath = "input.java";
        position = -1;
        // previousCharacter = "";
        // listOfVariables = new LinkedList<>();
        lineNumbers = new ArrayList<>();
    }

    protected void readFile() {
        try {
            reader = new Scanner(new File(codePath));
//            while (reader.hasNextLine()) {
//                holdNextLine = reader.nextLine();
//                if (holdNextLine.contains("//") || holdNextLine.contains(""))// '//' = comment
//                    continue;
//                if (!holdNextLine.equals("")) {
//                    lineNumbers.add(holdNextLine.substring(0, 3));
//                    input += holdNextLine + " ";
//                }
//            }
            lNum = 1;
            inputList = new ArrayList<String>();
            temp = new ArrayList<String>();
            while (reader.hasNextLine())
            {

                holdNextLine = reader.nextLine().replaceAll("\\p{Punct}", " $0 ").trim().replaceAll("  +", " ");
                input += holdNextLine + " ";
                //input = input.replaceAll("\\p{Punct}", " $0 ").trim().replaceAll(" +", " "); // split to
                temp = new LinkedList<>(Arrays.asList(holdNextLine.split(" ")));
                // sort out line number array
                for(int i = 0; i < temp.size(); i++)
                {
                    lineNumbers.add(String.valueOf(lNum));
                }
                lNum += 1;
                temp.clear();
            }
            reader.close();
            inputList = new LinkedList<>(Arrays.asList(input.split(" ")));
            System.out.print("\nLineNumList" + lineNumbers);
            System.out.print("\ninputList" + inputList);
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        }
    }

    protected void createTokens() throws IOException {
        /*
         * LEXEME - Sequence of characters matched by PATTERN forming the TOKEN PATTERN
         * - The set of rule that define a TOKEN TOKEN - The meaningful collection of
         * characters over the character set of the programming language ex:ID,
         * Constant, Keywords, Operators, Punctuation, Literal String [Token] [Informal
         * Description] [Sample Lexemes] if characters i, f if else characters e, l, s,
         * e else comparison < or > or <= or >= or == or != <=, != id letter followed by
         * letters and digits pi, score, D2 number any numeric constant 3.14159, 0,
         * 6.02e23 literal anything but ", surrounded by "'s "core dumped"
         */
        ArrayList<String> tokenType = new ArrayList<>();
        tokenType.add("System"); //0
        tokenType.add("out"); // 1
        tokenType.add("print");// 2
        tokenType.add("println");// 3
        tokenType.add("abstract"); // 4
        tokenType.add("boolean");// 5
        tokenType.add("break");// 6
        tokenType.add("byte");// 7
        tokenType.add("case");// 8
        tokenType.add("catch");// 9
        tokenType.add("class");// 10
        tokenType.add("continue");// 11
        tokenType.add("const");// 12
        tokenType.add("default");// 13
        tokenType.add("do");// 14
        tokenType.add("double");// 15
        tokenType.add("else");// 16
        tokenType.add("enum");// 17
        tokenType.add("extends");// 18
        tokenType.add("final");// 19
        tokenType.add("final");// 20
        tokenType.add("finally");// 21
        tokenType.add("float");// 22
        tokenType.add("for");// 23
        tokenType.add("interface");// 24
        tokenType.add("int");// 25
        tokenType.add("if");// 26
        tokenType.add("long");// 27
        tokenType.add("main");// 28
        tokenType.add("private");// 29
        tokenType.add("protected");// 30
        tokenType.add("public");// 31
        tokenType.add("short");// 32
        tokenType.add("static");// 33
        tokenType.add("switch");// 34
        tokenType.add("return");// 35
        tokenType.add("void"); // 36
        tokenType.add("volatile"); // 37
        tokenType.add("this"); // 38
        tokenType.add("throw");// 39
        tokenType.add("try");// 40
        tokenType.add("var");// 41
        tokenType.add("while");// 42
        tokenType.add("+");// 43
        tokenType.add("-");// 44
        tokenType.add("*");// 45
        tokenType.add("/");// 46
        tokenType.add("\""); // 47
        tokenType.add("="); // 48
        tokenType.add(">");// 49
        tokenType.add("<");// 50
        tokenType.add(">=");// 51
        tokenType.add("<=");// 52
        tokenType.add(":");// 53
        tokenType.add("^");// 54
        tokenType.add(";");// 55
        tokenType.add("(");// 56
        tokenType.add(")");// 57
        tokenType.add(".");//58

        // possible additons
        tokenType.add("String_Literal");// 59 maybe add character const literals too
        tokenType.add("Comments");// 60
        tokenType.add("ID");// 61
        tokenType.add("Int_Num");// 62
        tokenType.add("Float_Num");// 63
        tokenType.add("White_Space");// 64

        tokenType.add("Bool");// 65
        tokenType.add("Relational_Op");// 66
        tokenType.add("Arithmetic_Op");// 67
        tokenType.add("Assignment_Op");// 68





        // holds token info
        try {
            Files.deleteIfExists(Paths.get("output.txt"));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        }
        // gets our first character
        getNextString(); // get first line
        try {
            Output = new FileWriter("output.txt", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // 99:
        // Stores symbolTable in array
        String symbolTable[] = createSymbolTable("symbol.txt");
//        System.out.print("\nTYPE:"+symbolTable.getClass().getName());
        //System.out.print("\nSymbolTable"+Arrays.toString(symbolTable));

        // int lineCounter = 0;
        while (position < inputList.size()) {
            //handleColon();        
            for (int i = 0; i < symbolTable.length; i += 2) {
                // tokenize java print keyword set              
                System.out.print("\n"+symbolTable[i]+"\t");  
                System.out.print(currentString);               
                if (symbolTable[i].equals("public") && currentString.equals(symbolTable[i]))
                {
                    System.out.print("\nLexeme:" + symbolTable[i] + " Token:" + tokenType.get(31) + "\n");
                    Output.write("Lexeme:" + symbolTable[i] + " Token:" + tokenType.get(31) + "\n");
                    tokenInfo.add("public");

                    getNextString();
                    //handleColon();
                    //handleSemicolon();
                    break;
                }
                else if(symbolTable[i].equals("/") && currentString.equals(symbolTable[i]) && inputList.get(position+1).equals("/"))
                {
                    //// handles end of line comments.
                    int currln = position;
                    String comStr = "";

                    while(lineNumbers.get(currln).equals(lineNumbers.get(position)))
                    {
                        comStr = comStr + currentString;
                        getNextString();
                    }
                    listOfComments.add(lineNumbers.get(position-1)); //line number for comment
                    listOfComments.add(comStr); //comment string

                    tokenInfo.add("Comment");
                    
                    
                    Output.write("Lexeme:" + comStr + " Token:" + tokenType.get(60) + "\n");                    
                    
                    break;
                }
                else if (currentString.equals("")){
                    //skip empty strings and go to next lien
                    getNextString();
                    break;
                }
                else if(symbolTable[i].equals("\"") && currentString.equals(symbolTable[i]))
                {
                    //// handles string literals
                    String comStr = "";
                    comStr = comStr + currentString;
                    getNextString();
                    boolean next = true;
                    do{
                        if(currentString.toLowerCase().contains("\"")){
                            comStr = comStr + currentString;
                            getNextString();
                        }
                        else{
                            comStr = comStr + currentString;
                            getNextString();
                            next = false;
                        }
                    } while (next);
                    comStr = comStr + currentString;
                    // System.out.print("\nb4:"+currentString);
                    getNextString();
                    // System.out.print("\naft:"+currentString);

                    tokenInfo.add("System");
                    // System.out.print("\nLexeme:" + comStr + " Token:" + tokenType.get(59) + "\n");
                    // System.out.print("\n~~~"+comStr+"~~~"+lineNumbers.get(position-1));
                    Output.write("\nLexeme:" + comStr + " Token:" + tokenType.get(59) + "\n"); //****


                    listOfComments.add(lineNumbers.get(position-1)); //line number for comment
                    listOfComments.add(comStr); //comment string
                    break;
                }
              // getNextString();
            }            
            Output.close();
        }
        
        

    }

    private String[] createSymbolTable(String symbolTablePath) throws IOException {
        String[] symbolTable = {};
        boolean fileExists = false;
        String symbolTableContents = "";

        try {
            reader = new Scanner(new File(symbolTablePath));
            fileExists = true;
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        }

        if (fileExists) {
            while (reader.hasNextLine()) {
                symbolTableContents += reader.nextLine();
                //Output.write(symbolTableContents); //********************
                symbolTable = symbolTableContents.split ("\\|");
            }

        } else {
            System.out.println("File does not exist so symbol table could not be created");
        }
        return symbolTable;
    }

    private void handleColon() {
        if (currentString.endsWith(":") && !currentString.equals(":")) {

            String reformatCharacter = currentString.replace(":", "").trim();
            inputList.set(position, reformatCharacter);
            currentString = inputList.get(position);
            inputList.add(position + 1, ":");
            // dont change these position variables. add inserts before it and set replaces
            // at position
        }
    }

    private void handleSemicolon() {
        if (currentString.endsWith(";") && !currentString.equals(";")) {
            String reformatCharacter = currentString.replace(";", "").trim();
            inputList.set(position, reformatCharacter);
            currentString = inputList.get(position);
            inputList.add(position + 1, ";");
            // dont change these position variables. add inserts before it and set replaces
            // at position
        }
    }

    protected String getNextString() {
        // extracts a single line of the source as a string value
        position += 1;
        if (position < inputList.size())
            currentString = inputList.get(position);
        return currentString;
    }

    protected String getPreviousCharacter() {
        if (position > 0)
            tempCount = position - 1;
        hold = inputList.get(tempCount);
        return hold;
    }

    public static List<String> getinputList() {
        return inputList;
    }

    public static ArrayList<String> getTokenInfo() {
        return tokenInfo;
    }

    public static List<String> getLineNumbers() {
        return lineNumbers;
    }

    /*
     * public static parserFile getParserFile(){ return parserFile; }
     */

}