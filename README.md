# Orders_analyzer
Prototype java desktop application for a catering company

## Description
- Created a stand-alone Java desktop application that handled specific types of MS Word files. <br>
- The cloud-based application used by the Catering Company was not capable of creating a single document merging orders from several clients. <br>
- This java application is the prototype. <br>


## Overall
- Handles specific types of documents created by the cloud-based application. <br>
The cloud-based application created specific files for specific clients. <br>
Namely, Kitchen Breakdown sheet and Client-Specific sheets. <br>
These documents are included in the project under folder [Orders](Orders)
- Reads the client's order which is in MS Word, identifies bread types, product name and quantity ordered. <br>
And, creates an MS Word document that includes every client's order in a tabular format. <br>
- Inclusion of the word "Special" in both Kitchen Breakdown sheet and Client-specific sheet would make the final document have the client's name included along with the order. <br>
Usecase: If most cafes use Coral lattuce with Tuna in Panini bread and if cafe "A" uses Spinach, the inclusion of the word "Special" in cafe "A" document would make final document have a seperate row along with cafe "A" name so the production would know it is different Panini Tuna order than regular ones. <br>




## License & Copyright
[License](LICENSE).
