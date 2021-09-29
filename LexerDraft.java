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
    public static List<String> listOfStringLit = new ArrayList<>();
    public static ArrayList<String> symbolTable = new ArrayList<>();

    LexerDraft() {
        codePath = ProjectGUI.getInputPath();
        //codePath = "input.java";
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
        // Java keywords or reserved words


        tokenType.add("WHILE");              //0
        tokenType.add("FOR");                //1
        tokenType.add("SWITCH");             //2
        tokenType.add("CONDITIONAL");        //3
        tokenType.add("REFERENCE");          //4 --

        tokenType.add("KEYWORD");            //5
        tokenType.add("IDENTIFIER");         //6
        tokenType.add("SYMBOL");             //7

        tokenType.add("PRINT");              //8

        tokenType.add("COMPARISON");         //9 
        tokenType.add("ASSIGNMENT");         //10 
        tokenType.add("ARITHMETIC");         //11 

        tokenType.add("SEMICOLON");          //12
        tokenType.add("LPAREN");             //13
        tokenType.add("RPAREN");             //14
        tokenType.add("LBRACKET");           //15
        tokenType.add("RBRACKET");           //16
        tokenType.add("LBRACE");             //17
        tokenType.add("RBRACE");             //18

        tokenType.add("INTEGER");            //19
        tokenType.add("DOUBLE");             //20 --
        tokenType.add("FLOAT");              //21
        tokenType.add("STRING");             //22

        tokenType.add("WHITESPACE");         //23 --
        tokenType.add("COMMENT");            //24


        //POPULATE SYMBOL TABLE

        //KEYWORD
        symbolTable.add("System"); //java
        symbolTable.add("Console"); //c#
        symbolTable.add("out"); //java
        symbolTable.add("out"); //C#
        symbolTable.add("print");// java
        symbolTable.add("write");// C#
        symbolTable.add("println");// java
        symbolTable.add("WriteLine");// C#
        symbolTable.add("abstract"); //java
        symbolTable.add("abstract"); //C#
        symbolTable.add("boolean");// java
        symbolTable.add("bool");// C#
        symbolTable.add("break");// Java
        symbolTable.add("break");// C#
        symbolTable.add("byte");// Java
        symbolTable.add("byte");// C#
        symbolTable.add("catch");// Java
        symbolTable.add("catch");// C#
        symbolTable.add("class");// Java
        symbolTable.add("class");// C#
        symbolTable.add("continue");// Java
        symbolTable.add("continue");// C#
        symbolTable.add("const");// Java
        symbolTable.add("const");// C#
        symbolTable.add("default");// Java
        symbolTable.add("default");// C#
        symbolTable.add("enum");// java
        symbolTable.add("enum");// C#
        symbolTable.add("extends");// Java
        symbolTable.add("extends");// C#
        symbolTable.add("final");// Java
        symbolTable.add("sealed");// C#
        symbolTable.add("final");// Java
        symbolTable.add("readonly");// C# alternative!
        symbolTable.add("finally");// Java
        symbolTable.add("finally");// C#
        symbolTable.add("interface"); // Java
        symbolTable.add("interface"); // C#
        symbolTable.add("main");// Java
        symbolTable.add("main");// C#
        symbolTable.add("private");// Java
        symbolTable.add("private");// C#
        symbolTable.add("protected");// Java
        symbolTable.add("protected");// C#
        symbolTable.add("public");// Java
        symbolTable.add("public");// C#
        symbolTable.add("static");// Java
        symbolTable.add("static");// C#
        symbolTable.add("String");// Java
        symbolTable.add("string");// C#
        symbolTable.add("return");// Java
        symbolTable.add("return");// C#
        symbolTable.add("void"); // Java
        symbolTable.add("void"); // C#
        symbolTable.add("volatile"); // Java
        symbolTable.add("volatile"); // C#
        symbolTable.add("this"); // Java
        symbolTable.add("this"); // C#
        symbolTable.add("throw");// Java
        symbolTable.add("throw");// C#
        symbolTable.add("try");// Java
        symbolTable.add("try");// C#
        symbolTable.add("var");// Java
        symbolTable.add("var");// C#
        symbolTable.add("double");// Java
        symbolTable.add("double");// C#
        symbolTable.add("int");// Java
        symbolTable.add("int");// C#
        symbolTable.add("long");// Java
        symbolTable.add("long");// C#
        symbolTable.add("short");// Java
        symbolTable.add("short");// C#
        symbolTable.add("float");// Java
        symbolTable.add("float");// C#

        //FOR
        symbolTable.add("for");// Java
        symbolTable.add("for");// C#
        //CONDITIONAL
        symbolTable.add("if");// Java
        symbolTable.add("if");// C#
        symbolTable.add("else");// Java
        symbolTable.add("else");// c#
        //WHILE
        symbolTable.add("while");// Java
        symbolTable.add("while");// C#
        symbolTable.add("do");// Java
        symbolTable.add("do");// C#
        //SWITCH
        symbolTable.add("switch");// Java
        symbolTable.add("switch");// C#
        symbolTable.add("case");// Java
        symbolTable.add("case");// C#

        //ARITHMETIC
        symbolTable.add("+");// Java
        symbolTable.add("+");// C#
        symbolTable.add("-");// Java
        symbolTable.add("-");// C#
        symbolTable.add("*");// Java
        symbolTable.add("*");// C#
        symbolTable.add("/");// Java
        symbolTable.add("/");// C#
        //ASSIGNMENT
        symbolTable.add("="); // Java
        symbolTable.add("="); // C#
        //COMPARISON
        symbolTable.add(">");// Java
        symbolTable.add(">");// C#
        symbolTable.add("<");// Java
        symbolTable.add("<");// C#
        symbolTable.add(">=");// Java
        symbolTable.add(">=");// C#
        symbolTable.add("<=");// Java
        symbolTable.add("<=");// C#

        //SEMICOLON
        symbolTable.add(";");// Java
        symbolTable.add(";");// C#

        //SYMBOL
        symbolTable.add("\""); // Java
        symbolTable.add("\""); // C#
        symbolTable.add(".");// java
        symbolTable.add(".");// C#
        symbolTable.add(",");// Java
        symbolTable.add(",");// C#
        symbolTable.add(":");// Java
        symbolTable.add(":");// C#
        symbolTable.add("^");// Java
        symbolTable.add("^");// C#

        //LPAREN
        symbolTable.add("(");// Java
        symbolTable.add("(");// C#
        //RPAREN
        symbolTable.add(")");// Java
        symbolTable.add(")");// C#
        //LBRACKET
        symbolTable.add("[");//61
        symbolTable.add("[");//61
        //RBRACKET
        symbolTable.add("]");//62
        symbolTable.add("]");//62
        //RBRACE
        symbolTable.add("}");//63
        symbolTable.add("}");//63
        //LBRACE
        symbolTable.add("{");//64
        symbolTable.add("{");//64

        symbolTable.add("System.out.print");// Java
        symbolTable.add("Console.Write");// C#
        symbolTable.add("System.out.println");// Java
        symbolTable.add("Console.WriteLine");// C#

        System.out.println("\n index"+symbolTable.indexOf("float"));


        // holds token info
        try {
            Files.deleteIfExists(Paths.get("output.txt"));
            System.out.println("");
            System.out.println("deleted old output");
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
        //String symbolTable[] = createSymbolTable("symbol.txt");
//        System.out.print("\nTYPE:"+symbolTable.getClass().getName());
        //System.out.print("\nSymbolTable"+Arrays.toString(symbolTable));

        // int lineCounter = 0;
        String outString = "";
        while (position < inputList.size()) {
            //handleColon();
            for (int i = 0; i < symbolTable.size(); i += 2) {
                // tokenize java print keyword set
              //  System.out.print("\n"+symbolTable.get(i)+"\t");
              //  System.out.print(currentString);

                //List<String> namesList = Arrays.asList( Arrays.copyOfRange(names, 0, 2) );

                if (symbolTable.get(i).equals("while") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles WHILE token type
                    //System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(0) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(0) + "\n";
                    tokenInfo.add(tokenType.get(0));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("for") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles FOR token type
                    //System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(1) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(1) + "\n";
                    tokenInfo.add(tokenType.get(1));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("switch") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles SWITCH token type
                    //System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(2) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(2) + "\n";
                    tokenInfo.add(tokenType.get(2));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("if") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles SWITCH token type
                    //System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(3) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(3) + "\n";
                    tokenInfo.add(tokenType.get(3));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("else") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles SWITCH token type
                    //System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(3) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(3) + "\n";
                    tokenInfo.add(tokenType.get(3));
                    getNextString();
                    break;
                }
                else if (symbolTable.subList(0,36*2+1).contains(symbolTable.get(i)) && currentString.equals(symbolTable.get(i)))
                {
                    // Handles KEYWORDS token type (first 37 in the symbols table)
                    if(inputList.get(position).equals("System") && inputList.get(position+1).equals(".") && inputList.get(position+2).equals("out") && inputList.get(position+3).equals(".") && (inputList.get(position+4).equals("print") || inputList.get(position+4).equals("println")))
                    {
                        //PRINT token case (System.out.print or System.out.println)
                        int temp_position = position;
                        //System.out.print("\nLexeme: " + inputList.get(temp_position) + inputList.get(++temp_position) + inputList.get(++temp_position) + inputList.get(++temp_position) + inputList.get(++temp_position) + " Token:" + tokenType.get(8) + "\n");
                        
                        outString = outString +"Lexeme:" + inputList.get(position) + inputList.get(position+1) + inputList.get(position+2) + inputList.get(position+3) + inputList.get(position+4) + " Token:" + tokenType.get(8) + "\n";
                        tokenInfo.add(tokenType.get(8));
                        
                        for (int j = 0; j<5; j++) {
                          getNextString();  
                        } 
                        break;                       
                    }
                    else
                    {
                        //Other keywords
                        //System.out.print("\nLexeme: " + symbolTable.get(i) + " Token:" + tokenType.get(5) + "\n");
                        outString = outString +"Lexeme:" + symbolTable.get(i) + " Token:" + tokenType.get(5) + "\n";
                        tokenInfo.add(tokenType.get(5));
                    }
                    getNextString();
                    break;
                }
                else if(symbolTable.get(i).equals("\"") && currentString.equals(symbolTable.get(i)))
                {
                    //// handles string literals
                    String literStr = "";
                    literStr = literStr + currentString;
                    getNextString();
                    boolean next = true;
                    do{
                        literStr = literStr + currentString;
                        getNextString();
                    } while (!currentString.equals("\""));
                    literStr = literStr + currentString;
                    getNextString();
                    tokenInfo.add("String_Literals");
                    //System.out.print("\nLexeme:" + literStr + " Token:" + tokenType.get(22) + "\n");
                    outString = outString + "Lexeme:" + literStr + " Token:" + tokenType.get(22) + "\n"; //****
                    listOfStringLit.add(lineNumbers.get(position-1)); //line number for string
                    listOfComments.add(literStr); //comment string
                    break;
                }
                else if(symbolTable.get(i).equals("/") && currentString.equals(symbolTable.get(i)) && inputList.get(position+1).equals("/"))
                {
                    // END OF LINE COMMENTS
                    int currln = position;
                    String comStr = "";

                    while(lineNumbers.get(currln).equals(lineNumbers.get(position)))
                    {
                        comStr = comStr + currentString;
                        getNextString();
                    }
                    listOfComments.add(lineNumbers.get(position-1));
                    listOfComments.add(comStr);

                    // System.out.print("\nLexeme:" + comStr + " Token:" + tokenType.get(24) + "\n");
                    outString = outString + "Lexeme:" + comStr + " Token:" + tokenType.get(24) + "\n";
                    tokenInfo.add(tokenType.get(24));
                    break;
                }
                else if(symbolTable.get(i).equals("/") && currentString.equals(symbolTable.get(i)) && inputList.get(position+1).equals("*"))
                {
                    // MULTI-LINE COMMENTS
                    /*
                    *
                    */
                    int comStrLine = position;
                    String comStr = "";

                    boolean stop = false;
                    while(!stop)
                    {
                        if (inputList.get(position).equals("*") && inputList.get(position + 1).equals("/")) // if "*/" end multip line commen
                        {
                            // end of multi line comment
                            comStr = comStr + currentString;
                            getNextString(); // "*"
                            comStr = comStr + currentString;
                            getNextString(); // "/"
                            stop = true;
                        }
                        else
                        {
                            comStr = comStr + currentString;
                            getNextString();
                        }
                    }
                    listOfComments.add(lineNumbers.get(comStrLine));
                    listOfComments.add(comStr);

                    // System.out.print("\nLexeme:" + comStr + " Token:" + tokenType.get(24) + "\n");
                    outString = outString + "Lexeme:" + comStr + " Token:" + tokenType.get(24) + "\n";
                    tokenInfo.add(tokenType.get(24));
                    break;
                }
                else if (symbolTable.subList(54*2,58*2+1).contains(symbolTable.get(i)) && currentString.equals(symbolTable.get(i)))
                {
                    // Handles SYMBOLS token type
                    // System.out.print("\nLexeme: " + symbolTable.get(i) + " Token:" + tokenType.get(7) + "\n");
                    outString = outString +"Lexeme:" + symbolTable.get(i) + " Token:" + tokenType.get(7) + "\n";
                    tokenInfo.add(tokenType.get(7));
                    getNextString();
                    break;
                }
                else if (symbolTable.subList(44*2,47*2+1).contains(symbolTable.get(i)) && currentString.equals(symbolTable.get(i)))
                {
                    // Handles ARITHMETIC token type
                    // System.out.print("\nLexeme: " + symbolTable.get(i) + " Token:" + tokenType.get(11) + "\n");
                    outString = outString +"Lexeme:" + symbolTable.get(i) + " Token:" + tokenType.get(11) + "\n";
                    tokenInfo.add(tokenType.get(11));
                    getNextString();
                    break;
                }
                else if (symbolTable.subList(49*2,52*2+1).contains(symbolTable.get(i)) && currentString.equals(symbolTable.get(i)))
                {
                    // Handles COMPARISON token type
                    if((inputList.get(position).equals(">") && inputList.get(position + 1).equals("=")) || (inputList.get(position).equals("<") && inputList.get(position + 1).equals("=")))
                    {
                        //case of >= or <=
                        // System.out.print("\nLexeme: " + symbolTable.get(i+4) + " Token:" + tokenType.get(9) + "\n");
                        outString = outString +"Lexeme:" + symbolTable.get(i+4) + " Token:" + tokenType.get(9) + "\n";
                        getNextString();
                    }
                    else
                    {
                        // System.out.print("\nLexeme: " + symbolTable.get(i) + " Token:" + tokenType.get(9) + "\n");
                        outString = outString +"Lexeme:" + symbolTable.get(i) + " Token:" + tokenType.get(9) + "\n";
                    }
                    tokenInfo.add(tokenType.get(9));
                    getNextString();
                    break;
                }
                else if(symbolTable.get(i).equals("=") && currentString.equals(symbolTable.get(i)))
                {
                    // Handle ASSIGNMENT tokens
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(10) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(10) + "\n";
                    tokenInfo.add(tokenType.get(10));
                    getNextString();
                    break;
                }

                else if (currentString.equals("")){
                    //skip empty strings and go to next lien
                    getNextString();
                    break;
                }

                else if (symbolTable.get(i).equals(";") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles SEMICOLON token type
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(12) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(12) + "\n";
                    tokenInfo.add(tokenType.get(12));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("(") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles LPAREN token type
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(13) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(13) + "\n";
                    tokenInfo.add(tokenType.get(13));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals(")") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles RPAREN token type
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(14) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(14) + "\n";
                    tokenInfo.add(tokenType.get(14));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("[") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles LBRACKET token type
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(15) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(15) + "\n";
                    tokenInfo.add(tokenType.get(15));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("]") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles RBRACKET token type
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(16) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(16) + "\n";
                    tokenInfo.add(tokenType.get(16));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("{") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles LBRACE token type
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(17) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(17) + "\n";
                    tokenInfo.add(tokenType.get(17));
                    getNextString();
                    break;
                }
                else if (symbolTable.get(i).equals("}") && currentString.equals(symbolTable.get(i)))
                {
                    // Handles RBRACE token type
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(18) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(18) + "\n";
                    tokenInfo.add(tokenType.get(18));
                    getNextString();
                    break;
                }
                else if (!symbolTable.contains(currentString) && currentString.matches("^\\d+$"))  // if value doesn't match anything in the symbol table AND is an integer
                {
                    //handles integers
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(19) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(19) + "\n";
                    tokenInfo.add(tokenType.get(19));
                    getNextString();
                    break;
                }    
                   
                
                else if (!symbolTable.contains(currentString) && currentString.matches("^([+-]?\\d*\\.?\\d*)$"))  // if value doesn't match anything in the symbol table AND is an float
                {
                    //handle float
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(21) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(21) + "\n";
                    tokenInfo.add(tokenType.get(21));
                    getNextString();
                    break;
                }
                else if (!symbolTable.contains(currentString))
                {
                    // Handles IDENTIFIERS
                    // System.out.print("\nLexeme: " + currentString + " Token:" + tokenType.get(6) + "\n");
                    outString = outString + "Lexeme:" + currentString + " Token:" + tokenType.get(6) + "\n";
                    tokenInfo.add(tokenType.get(6));
                    getNextString();
                    break;
                }
                // getNextString();
            }
//            getNextString();
        }
        Output.write(outString);
        Output.close();
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
                symbolTable = symbolTableContents.split ("\\|");            }

        } else {
            System.out.println("File does not exist so symbol table could not be created");
        }
        reader.close();
        return symbolTable;
    }

   /* private void handleColon() {
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
    */

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