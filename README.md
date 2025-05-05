# 💰 Personal Ledger CLI Application

A simple command-line Java application that functions as a personal ledger, helping users track deposits, payments, balances, and generate detailed reports. All transactions are saved to a mutable .csv file that contents can be accessed by the user at any point in time. 

---

## 🧾 Features

- 📋 **View All Transactions**  
  Displays a full list of all ledger entries.

- ➕ **Deposits View**  
  Filters and displays only deposit (positive) transactions.

- ➖ **Payments View**  
  Filters and displays only payment (negative) transactions.

- 💵 **Balance Check**  
  Calculates and displays current balance with color-coded output.

- 📊 **Reports Menu**  
  Analyze transactions with options like:
  - Month to Date
  - Previous Month
  - Year to Date
  - Previous Year
  - Search by Vendor
  - Custom Search by date, description, vendor, or amount
---
### Application Screens

![Screenshot 2025-05-04 195233](https://github.com/user-attachments/assets/b30c850f-4b7d-48bd-9f10-5b6abe8d4404)

Deposit Added.

![Screenshot 2025-05-04 195320](https://github.com/user-attachments/assets/03e7fce3-5cf5-4de6-9558-2e1e998a9c34)

All entries displayed.

![Screenshot 2025-05-04 195421](https://github.com/user-attachments/assets/1ae49ee5-4232-418a-abef-8f1239f5cb2c)

Deposits Only.

![Screenshot 2025-05-04 195443](https://github.com/user-attachments/assets/ab3b511e-75e8-4bcd-a188-a6f5d417b52c)

Payments Only

![Screenshot 2025-05-04 195509](https://github.com/user-attachments/assets/0c437357-9b84-47bb-b3c3-73b60a57a6f6)

Balance Display

![Screenshot 2025-05-04 195641](https://github.com/user-attachments/assets/0d132f5d-8bee-48f6-bc06-55d677236eef)

Search by Vendor. Partial search included. 

![Screenshot 2025-05-04 195823](https://github.com/user-attachments/assets/980332a4-3a94-4396-8a09-519e2ac8eed9)

Custom Search accessed from the Report’s menu 
---
#### Code I Learned the Most From
![Screenshot 2025-05-04 192417](https://github.com/user-attachments/assets/f28e13fa-a701-45d6-b5e0-55dfb6c3fa91)

This snippet of code wasn’t necessarily the hardest for me to figure out, but it was the most satisfying. The point of having a prompt that allows the user to try and search for a vendor multiple times is intuitive and user friendly, but if done incorrectly you can cause a backlog on the call stack. I didn’t realize that by telling the machine to redo my “searchByVendor()” method every time the user inputted “Y”, I was adding to this stack which would not allow my program to end unless the whole method was complete. This means for every “Y” the user inputted on the screen the machine would look for an “N” to end that method and exit the loop I created. This example of the wrong way of recursion was eye opening for a beginner software engineer like myself. I had that “ah ha” moment of why it is so important knowing about the bytes of a system and the ability to understand and implement the use of static and global variables to prevent infinite recursion. Though it is rare due to most systems handling this issue, I still had to learn about this to understand why it wasn’t the best decision. Creating a exact and specific tree-like structure to manage your code is important to see how deep function or method calls go.
---

