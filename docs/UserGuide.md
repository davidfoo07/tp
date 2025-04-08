---
layout: page
title: HRelper User Guide
---
--------------------------------------------------------------------------------------------------------------------
## Hello HR! Meet HRelper
Welcome to HRelper, your trusted assistant for managing employee records with ease and speed.
We understand that working in HR means wearing many hats — from onboarding new hires and updating staff details to managing departments and tracking employment history. It can quickly become overwhelming.

That’s where HRelper comes in. Designed to simplify and streamline your workflow, HRelper helps you manage your employee database efficiently and confidently — so you can spend less time on spreadsheets and more time focusing on people.

--------------------------------------------------------------------------------------------------------------------

### Table of Contents

- [Quick start](#quick-start)
- [Features](#features)
    - [Viewing help](#viewing-help--help)
    - [Adding a person](#adding-a-person-add)
    - [Listing all persons](#listing-all-persons--list)
    - [Viewing a profile](#viewing-a-profile-view)
      - [Returning to home view](#returning-to-home-view)
    - [Editing a person](#editing-a-person--edit)
    - [Filtering by](#filtering-by-findby)
    - [Deleting a person](#deleting-a-person--delete)
    - [Adding a note](#adding-a-note--note)
    - [Clearing all entries](#clearing-all-entries--clear)
    - [Exiting the program](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
    - [Archiving data files](#archiving-data-files-coming-in-v20)
- [FAQ](#faq)
    - [Q1: Will my data be lost if I close the app?](#a-idq1aq1-what-happens-if-i-close-the-app-will-my-data-be-lost)
    - [Q2: Can I search by job title or department?](#a-idq2aq2-can-i-search-for-employees-by-job-title-or-departmentbr)
- [Known issues](#known-issues)
- [Command summary](#command-summary)
- [Valid Departments, Employment Types, Job Titles](#valid-departments-employment-types-job-titles-of-hrelper)
- [Valid Short-Forms](#valid-short-forms-of-hrelper)

--------------------------------------------------------------------------------------------------------------------
## ⚠️ Current HRelper Prototype Overview
The current version of the HRelper is a prototype designed to showcase its core functionality.

* At present, the prototype is tailored to Singapore, meaning that phone numbers and NRICs are configured for Singapore-based operations.
* Since this is just a prototype of how our HRelper works, in real world scenarios, we will customise the valid department list according to the company's needs. Each company should populate the valid department before using HRelper.
* Attributes marked with an asterisk (*) indicate that they are in their current form because they follow the structure of the prototype.

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-F14-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for HRelper.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar "[CS2103T-F14-02][HRelper]".jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds.
   
![IntroPic](images/IntroPic.png) <br>
<br>
    This is how the app should look like after containing some data:

![Ui](images/Ui.png)

If there is no sample data, it could be where under the same directory of this jar file, there is a data file and inside there is a addressbook.json file. For eg: ./data/addressbook.json, please remove it for sample data to load. 

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts with full attributes.

   * `add n/John Doe p/98765432 e/johnd@example.com ic/T0312345A g/Male d/02-Jan-2001 j/15-Apr-2025 nat/Singaporean a/311, Clementi Ave 2, #02-25/119278 t/Finance/Full-Time/Financial Analyst` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `view Lee` : Views the contact by their surname.

   * `edit 2 n/Betsy Crower`: Edits the name of the 2nd person to be `Betsy Crower`.

   * `findByDepartment Finance` : Lists all contacts in this specific department

   * `note`: Adds optional remarks to people in their address book and edit it if required.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Words in `SQUARE BRACKETS [ ]` are optional parameters. <br>
  e.g. in `edit 1 [n/NAME]`, `NAME` is an optional parameter

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC g/GENDER d/DOB j/DATE OF JOINING nat/NATIONALITY a/ADDRESS/POSTAL CODE t/DEPARTMENT/EMPLOYMENTTYPE/JOBTITLE`

* Names are case-insensitive. The profile will automatically capitalise the first letter of each word and convert all other letters to lowercase.
* Phone numbers must be exactly 8 digits long and start with 6, 8, or 9.*
* NRIC should start with S, T, F, or G, followed by 7 digits, and end with a capital letter.*
* Gender is case-insensitive but will only take in Male, Female or Other.*
* All dates (DOB or Date of Joining) must follow one of these formats: dd-MMM-yyyy, dd/MM/yyyy, dd.MM.yyyy, yyyy-MM-dd, or dd-MM-yyyy ->
  **Single-digit days and months must be zero-padded (e.g., use 01 instead of 1)**
* DOB must be a valid date that is not in the future.
* Date of Joining must be a valid date that is after DOB. Future dates are allowed to account for upcoming hires. (If the contract date is yet to be confirmed, please select the first day of the joining month.)
* Nationality is case-insensitive but follows a pre-defined set of common nationalities. In the rare case where a nationality is not specified, choose 'Other'.
* Address follows postal codes (specifically, the Postal Sector) defined by the [Urban Redevelopment Authority (URA)](https://www.ura.gov.sg/Corporate/-/media/Corporate/Property/PMI-Online/List_Of_Postal_Districts.pdf).
* Tag fields (i.e. Department, Employment Type, Job Title) are case-insensitive. Additionally, certain short forms are valid for Department.

**To note: Each person is unique based on their NRIC**


Examples:
* `add n/John Doe p/98765432 e/johnd@example.com ic/T0312345A g/Male d/02-Jan-2001 j/15-Apr-2025 nat/Singaporean a/311, Clementi Ave 2, #02-25/119278 t/Finance/Full-Time/Financial Analyst`


### Listing all persons : `list`

Shows a list of all persons in the address book with full attributes.

Format: `list`

### Viewing a profile: `view`

Displays the full profile of an employee by matching their **full name** or **partial name**.

Format: `view FULLNAME` or `view PARTIALNAME`

* A partial name: one FULL word of a full name<br> e.g `David` of `David Li Wen Jun`
* The search is **case-insensitive**.<br> e.g `hans` will match `Hans`
* ⚠️ Order matters for full names.<br> e.g. `Hans Bo` will not match `Bo Hans`
* Only the name is searched.
* **Exact word match only** - prefix names won't be matched.<br> e.g. `Han` will not match `Hans`
* Persons matching the partial name or full name will be returned (i.e. `OR` search).<br>
  e.g. `view Bo` will return name with same partial name  `Hans Bo`, `Ling Bo` <br> e.g. `view Hans Bo` will return `Hans Bo`

Examples:
* `view David Li`
* `view David`
* `view Li`

![view_profile](images/ViewImage.png)

### Returning to Home View:

After viewing an employee’s profile, you can return to the main Home view, simply:

- **Click** on **Home → Back to Home** in the top menu bar

HRelper will return to the overview page and display all employees again, with a message:
**Returned to Home**

<table> <tr> <td align="center"><strong>Before returning to Home</strong><br><br> <img src="images/BeforeHome.png" alt="Before returning to home" width="400"/> </td> <td align="center"><strong>After returning to Home</strong><br><br> <img src="images/AfterHome.png" alt="images/AfterHome" width="400"/> </td> </tr> </table>
<div markdown="span" class="alert alert-warning">SHORTCUT: press F2 to navigate back to home page</div>

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [g/GENDER] [d/DOB] [j/DATE OF JOINING] [nat/NATIONALITY] [a/ADDRESS/POSTAL CODE] [t/DEPARTMENT/EMPLOYMENT TYPE/JOB TITLE]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* Square brackets [ ] represent optional fields
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, even if editing only one item out of all, you need to include all items
* Note that editing of NRIC is not allowed
* *Refer back to `add` command for validity of inputs


Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower` Edits the name of the 2nd person to be `Betsy Crower`.
*  `edit 1 t/HR/Full-Time/HR Coordinator` Edits the tag of the 1st person to be `HR/Full-Time/HR Coordinator`.

### Filtering by: `findBy...`
Filters the contacts by their department, job title, or employment type.

*Refer to table of valid inputs and short-forms for HRelper

#### `findByDepartment`
Format: `findByDepartment KEYWORD`

* Finds contacts who belong to departments that match the specified keyword.
* Search is case-insensitive (e.g., "finance" will match "Finance").
* Accepts department short forms (e.g., "HR" will match "Human Resources").
* For partial matches, keyword must contain at least 3 consecutive letters of the department name.
* Only alphabetic characters and the '&' symbol are allowed in department search terms.

Examples:
* `findByDepartment Finance` returns all contacts in the Finance department.
* `findByDepartment HR` returns all contacts in the Human Resources department.
* `findByDepartment Inf` returns all contacts in the Information Technology department.
* `findByDepartment Tec` returns all contacts in the Information Technology department.

#### `findByJobTitle`
Format: `findByJobTitle KEYWORD`

* Finds contacts whose job titles match the specified keyword.
* Search is case-insensitive.
* Will match if the keyword matches a full word in the job title.
* For partial matches, keyword must contain at least 3 consecutive letters of a word in the job title.
* Only alphabetic characters are allowed in job title search terms.

Examples:
* `findByJobTitle Engineer` returns all engineers.
* `findByJobTitle Dev` returns all developers.
* `findByJobTitle Coord` returns all coordinators.

#### `findByEmploymentType`
Format: `findByEmploymentType EMPLOYMENT_TYPE`

* Finds contacts with the specified employment type.
* Search is case-insensitive.
* Will match if the keyword matches a full word in the job title.
* For partial matches, keyword must contain at least 3 consecutive letters of a word in the job title.

Examples:
* `findByEmploymentType Part-Time` returns all part-time employees.
* `findByEmploymentType part` returns all part-time employees.
* `findByEmploymentType Full-Time` returns all full-time employees (e.g shown below).
* `findByEmploymentType ful` returns all full-time employees.
  
![findByEmploymentType](images/findByEmploymentType.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `findByDepartment Finance` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a note : `note`

Adds a note to the specified person from the address book.

Format: `note INDEX [NOTE]`

* Adds a note to the person at the specified `INDEX`. The index refers to the position of the person in the currently displayed list on the GUI. The index **must be a positive integer** 1, 2, 3, …​
* Square brackets [ ] represent optional fields.
* `NOTE` can be any string input.
* Existing note value will be updated to the input value.
* If the input is an empty string, the note will be reset.
<br> `note 1`

Examples:
* `note 1 they/them`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HRelper data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HRelper data are saved automatically as a JSON file `[JAR file location]/data/hrelper.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HRelper will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

### <a id="q1"></a>Q1: What happens if I close the app? Will my data be lost?
**A**: No worries! HRelper automatically saves all your data after every change. When you reopen the app, your latest data will still be there.

### <a id="q2"></a>Q2: Can I search for employees by job title or department?<br>
**A**: Yes! Use commands like `findByDepartment`, `findByJobTitle`, or `findByEmploymentType`.
Example: `findByDepartment Marketing` will show all employees in the Marketing department.


--------------------------------------------------------------------------------------------------------------------
## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.
3. HRelper currently does not validate whether email addresses are real, allowing users to input non-existent or invalid emails.
4. HRelper does not currently verify the authenticity of user-provided addresses, which may result in the submission of inaccurate or non-existent locations.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC g/GENDER d/DOB j/DATE OF JOINING nat/NATIONALITY a/ADDRESS/POSTAL CODE t/DEPARTMENT/EMPLOYMENTTYPE/JOBTITLE`<br>e.g., `add n/John Doe p/98765432 e/johnd@example.com ic/T0312345A g/Male d/02-Jan-2001 j/15-Apr-2025 nat/Singaporean a/311, Clementi Ave 2, #02-25/119278 t/Finance/Full-Time/Financial Analyst`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [d/DOB] [j/DATE OF JOINING] [nat/NATIONALITY] [a/ADDRESS/POSTAL CODE] [t/DEPARTMENT/EMPLOYMENT TYPE/JOBTITLE]`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find by Department** | `findByDepartment KEYWORD`<br> e.g., `findByDepartment HR`, `findByDepartment Fin`, `findByDepartment Information Technology` |
**Find by Job Title** | `findByJobTitle KEYWORD`<br> e.g., `findByJobTitle Engineer`, `findByJobTitle Dev`, `findByJobTitle Coordinator` |
**Find by Employment Type** | `findByEmploymentType EMPLOYMENT_TYPE`<br> e.g., `findByEmploymentType Full-Time`, `findByEmploymentType Contract` |
**Note** | `note INDEX NOTE` <br> e.g. note 1 he likes aadvarks
**List** | `list`
**Help** | `help`
**View** | `view FULLNAME` `view PARTIALNAME` <br> e.g., `view Alex Yeoh` or `view Yeoh`

## Valid Departments, Employment Types, Job Titles of HRelper

Here are the valid inputs for the respective fields in this particular working prototype. <br>
*Companies should customise such fields to fit their own business needs

Fields | Valid Inputs
--------|------------------
**Departments** |`Human Resources`, `Finance`, `Accounting`, `Marketing`, `Sales`, `Customer Service`, `Information Technology`, `Research and Development`, `Operations`, `Legal`, `Supply Chain & Logistics`, `Procurement & Purchasing`, `Engineering`, `Quality Assurance`, `Product Management`, `Manufacturing`, `Public Relations`, `Corporate Communications`, `Compliance & Risk Management`, `Business Development`, `Data Science`, `Cybersecurity`, `Software Development`, `UX/UI Design`, `Artificial Intelligence & Machine Learning`, `Training & Development`, `Facilities Management`, `Administration`, `Health & Safety`, `Diversity, Equity & Inclusion`
**Employment Types** |`Full-Time`, `Part-Time`, `Contract`, `Temporary`, `Internship`, `Freelance`, `Apprenticeship`, `Remote`, `Hybrid`
**Job Titles** | `Software Engineer`, `Data Analyst`, `Product Manager`, `HR Coordinator`, `Marketing Specialist`, `Sales Associate`, `Financial Analyst`, `Operations Manager`, `UX Designer`, `Project Manager`, `Business Consultant`, `Mechanical Engineer`, `Graphic Designer`, `Customer Support Representative`, `IT Technician`, `Electrical Engineer`, `Legal Advisor`, `Healthcare Administrator`, `Content Writer`, `Cybersecurity Analyst`, `Network Engineer`, `Quality Assurance Tester`, `Recruitment Specialist`, `Social Media Manager`, `Supply Chain Manager`
**Nationalities** |`Afghan`, `Albanian`, `Algerian`, `American`, `Andorran`, `Angolan`, `Argentine`,`Armenian`, `Australian`, `Austrian`, `Azerbaijani`, `Bahamian`, `Bahraini`,`Bangladeshi`, `Barbadian`, `Belarusian`, `Belgian`, `Belizean`, `Beninese`,`Bhutanese`, `Bolivian`, `Bosnian`, `Botswanan`, `Brazilian`, `British`,`Bruneian`, `Bulgarian`, `Burkinabé`, `Burmese`, `Burundian`, `Cambodian`, `Cameroonian`, `Canadian`, `Cape Verdean`, `Central African`, `Chadian`,`Chilean`, `Chinese`, `Colombian`, `Comorian`, `Congolese`, `Costa Rican`,`Croatian`, `Cuban`, `Cypriot`, `Czech`, `Danish`, `Djiboutian`, `Dominican`,`Dutch`, `Ecuadorian`, `Egyptian`, `Emirati`, `Equatorial Guinean`,`Eritrean`, `Estonian`, `Ethiopian`, `Fijian`, `Filipino`, `Finnish`, `French`,`Gabonese`, `Gambian`, `Georgian`, `German`, `Ghanaian`, `Greek`, `Grenadian`,`Guatemalan`, `Guinean`, `Guyanese`, `Haitian`, `Honduran`, `Hungarian`,`Icelandic`, `Indian`, `Indonesian`, `Iranian`, `Iraqi`, `Irish`, `Israeli`,`Italian`, `Ivorian`, `Jamaican`, `Japanese`, `Jordanian`, `Kazakh`, `Kenyan`,`Kiribati`, `Kuwaiti`, `Kyrgyz`, `Laotian`, `Latvian`, `Lebanese`, `Liberian`,`Libyan`, `Liechtenstein`, `Lithuanian`, `Luxembourgish`, `Malagasy`, `Malawian`,`Malaysian`, `Maldivian`, `Malian`, `Maltese`, `Marshallese`, `Mauritanian`,`Mauritian`, `Mexican`, `Micronesian`, `Moldovan`, `Monacan`, `Mongolian`,`Montenegrin`, `Moroccan`, `Mozambican`, `Namibian`, `Nauruan`, `Nepalese`,`New Zealander`, `Nicaraguan`, `Nigerien`, `Nigerian`, `North Korean`,`North Macedonian`, `Norwegian`, `Omani`, `Pakistani`, `Palauan`, `Palestinian`,`Panamanian`, `Papua New Guinean`, `Paraguayan`, `Peruvian`, `Polish`, `Portuguese`,`Qatari`, `Romanian`, `Russian`, `Rwandan`, `Saint Lucian`, `Salvadoran`, `Samoan`,`Saudi Arabian`, `Scottish`, `Senegalese`, `Serbian`, `Seychellois`, `Sierra Leonean`,`Singaporean`, `Slovak`, `Slovenian`, `Solomon Islander`, `Somali`, `South African`,`South Korean`, `South Sudanese`, `Spanish`, `Sri Lankan`, `Sudanese`, `Surinamese`,`Swazi`, `Swedish`, `Swiss`, `Syrian`, `Tajik`, `Tanzanian`, `Thai`, `Timorese`,`Togolese`, `Tongan`, `Trinidadian`, `Tunisian`, `Turkish`, `Turkmen`, `Tuvaluan`,`Ugandan`, `Ukrainian`, `Uruguayan`, `Uzbek`, `Vanuatuan`, `Venezuelan`, `Vietnamese`,`Welsh`, `Yemeni`, `Zambian`, `Zimbabwean`, `Other`
# Valid Short-Forms of HRelper

Please refer to the table for some valid short-form `department` tag inputs. The `department` to `short-form name` mapping is as follows

| Department Name                                | Short-form |
|------------------------------------------------|-----------|
| **Human resources**                            | `HR`      |
| **Customer Service**                           | `CS`      |
| **Information Technology**                     | `IT`      |
| **Research and Development**                   | `R&D`     |
| **Supply Chain & Logistics**                   | `SCM`     |
| **Procurement & Purchasing**                   | `Procurement` |
| **Quality Assurance**                          | `QA`      |
| **Product Management**                         | `PR`      |
| **Corporate Communications**                   | `CorpComm`|
| **Business Development**                       | `BizDev`  |
| **Data Science**                               | `DS`      |
| **Cybersecurity**                              | `CyberSec` |
| **Software Development**                       | `SD`      |
| **UX/UI Design**                               | `UX&UI`   |
| **Artificial Intelligence & Machine Learning** | `AI&ML`   |
| **Training & Development**                     | `T&D`     |
| **Facilities Management**                      | `FM`      |
| **Health & Safety**                            | `H&S`     |
| **Diversity, Equity & Inclusion**              | `DEI`     |

Note: `Finance` `Accounting` `Marketing` `Sales` `Operations` `Legal` `Engineering`
`Manufacturing` `Public Relations` `Administration` tags do not have valid short-forms
