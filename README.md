# Orders_analyzer
Prototype java desktop application for a catering company

## Description
- Created a stand-alone Java desktop application that handled specific types of MS Word files. <br>
- The cloud-based application used by the Catering Company was not capable of creating a single document analysing and merging orders from several clients. <br>
- This java application is the prototype addressing this particular limitation. <br>


## Overall
- Handles specific types of documents created by the cloud-based application. <br>
The cloud-based application created specific files for specific clients. <br>
Namely, Kitchen Breakdown sheet and Client-Specific sheets. <br>
These documents are included in the project under folder [Orders](Orders)
- Reads the client's order which is in MS Word, identifies bread types, product name and quantity ordered. <br>
And, creates a MS Word document that includes every client's order in a tabular format. <br>
- Inclusion of the word "Special" in both Kitchen Breakdown sheet and Client-specific sheet would make the final document have the client's name included along with the order. <br>
Usecase: If most cafes use Coral lattuce with Tuna in Panini bread and if cafe "A" uses Spinach, the inclusion of the word "Special" in cafe "A" document would make final document have a seperate row along with cafe "A" name so the production would know it is different Panini Tuna order than regular ones. <br>
- Creates "Combined document.docx" which is the combination of all orders. <br>
This document is analysed and then "Analysed clients.doc"; names of clients inside directory, and "Final analysis.docx"; final analysis, are created with time stamp seperated by "-" instead of ":". <br>
- Logins **Username**= qwerty; **Password**= 1234<br>

## Codes
- Uses Apache POI 4.1.1 to read MS file. 
- DirectoryStream<Path> is used to read the contents (orders) inside the choosen production directory. 
- Main data structure where the bread type, product name and quantity are added. <br>
  Map<String, TreeMap<String, Integer>> finalMapOfProducts = new TreeMap <>(); in [BreadType.java](src/main/java/sample/poi/classification/BreadType.java) <br> 
  Map<BreadName, ProductName, NumberOrdered> <br>
- [KitchenBreakdownReader class](src/main/java/sample/poi/read/KitchenBreakdownReader.java) and [ClientOrderReader class](src/main/java/sample/poi/read/ClientOrderReader.java) reads "Combined document.docx" for analysis automatically. It is hard coded in the classes.
- Iterator<XWPFTable> is used to read "Combined document.docx" table by table. 
- Login method uses String equals method being a prototype.
  
## Project structure
- ![image](https://github.com/qwertyfox/Orders_analyzer/blob/master/Project%20structure.png)


## License & Copyright
[License](LICENSE).
