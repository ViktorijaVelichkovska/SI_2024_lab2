# SI_2024_lab2_226036
Viktorija Velichkovska 226036


Control Flow Graph

![si_lab2_slika](https://github.com/ViktorijaVelichkovska/SI_2024_lab2_226036/assets/139005471/febe9bec-0f92-42f9-b0f5-e7870ef5fce7)



3. Цикломатска комплексност


-Почесто користен начин за пресметување на цикломатската сложеност е да се изброи бројот на точки на одлучување (услови) во дадениот код и да се додаде 1. Точките на одлучување вклучуваат if, while, for… и операторите ( &&, ||).

public class SILab2   {

    public static boolean checkCart(List<Item> allItems, int payment)
    {
        if (allItems == null) {         // Точка на одлучување 1
            throw new RuntimeException("allItems list can't be null!");
        }
        float sum = 0;

        for (int i = 0; i < allItems.size(); i++)  {        // Точка на одлучување 2
            Item item = allItems.get(i);
            if (item.getName() == null || item.getName().length() == 0) {   // Точка на одлучување 3 
                item.setName("unknown");
            }
            if (item.getBarcode() != null) {           // Точка на одлучување 4
                String allowed = "0123456789";
                char chars[] = item.getBarcode().toCharArray();
                for (int j = 0; j < item.getBarcode().length(); j++) {       // Точка на одлучување 5
                    char c = item.getBarcode().charAt(j);
                    if (allowed.indexOf(c) == -1) {  		// Точка на одлучување 6
                        throw new RuntimeException("Invalid character in item barcode!");
                    }
                }
                if (item.getDiscount() > 0) {            // Точка на одлучување 7
                    sum += item.getPrice() * item.getDiscount();
                } else {
                    sum += item.getPrice();
                }
            } 
       else {
                throw new RuntimeException("No barcode!");
            }
            
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0') {  	// Точка на одлучување 8
                sum -= 30;
            }
        }
        
        if (sum <= payment) {         // Точка на одлучување 9
            return true;
        } else {
            return false;
        }
    }
}

Постојат 9 точки на одлучување, што значи:
Cyclomatic Complexity  = 9 + 1 = 10






4. Every Branch критериум

Test Case 1: 
Input: allItems = null, payment = 100
Expected Output: RuntimeException со пораката "allItems list can't be null!"
Овој тест пример го покрива случајот каде сите items ќе се нули

Test Case 2: 
Input: allItems = [new Item(null, "67890", 200, 0.2f)], payment = 200
Expected Output: true
Овој тест пример ја препокрива branch каде имаме items чие име е null и треба да биде поставено на “unknown”.

Test Case 3: 
Input: allItems = [new Item("", "67890", 200, 0.2f)], payment = 200
Expected Output: true
Овој тест пример ја препокрива branch каде името на item има вредност празен string и треба да се постави на “unknown”

Test Case 4: 
Input: allItems = [new Item("item2", null, 200, 0.2f)], payment = 200
Expected Output: RuntimeException("No barcode!")
Овој тест пример ја препокрива branch каде баркодот на item е null, по што треба да се фрли исклучок.

Test Case 5: 
Input: allItems = [new Item("item3", "123b5", 200, 0.2f)], payment = 200
Expected Output: RuntimeException("Invalid character in item barcode!")
Овој тест пример ја препокрива branch каде баркодот има невалидни карактери

Test Case 6: 
Input: allItems = [new Item("item4", "67890", 150, 0.1f)], payment = 150
Expected Output: true
Овој тест пример ја препокрива branch каде item има попуст и во вкупната сума се пресметува и попустот

Test Case 7: 
Input: allItems = [new Item("item5", "67890", 150, 0)], payment = 150
Expected Output: true
Овој тест пример ја препокрива branch каде item нема попуст и вкупната сума е вкупната цена

Test Case 8: 
Input: allItems = [new Item("item6", "098765", 350, 0.3f)], payment = 100
Expected Output: false
Овој тест пример ја препокрива branch каде цената на item е поголема од 300, има попуст и баркодот започнува со цифра 0, што доведува до намалување на сумата за 30

Test Case 9: 
Input: allItems = [new Item("item7", "54321", 100, 0.1f), new Item("item8", "98765", 75, 0.2f)], payment = 140
Expected Output: true
Овој тест пример ја препокрива branch каде вкупниот збир на цените (вклучувајќи ги и попустите) е помал или еднаков на износот на уплатата.

Test Case 10: 
Input: allItems = [new Item("item9", "54321", 100, 0.1f), new Item("item10", "98765", 75, 0.2f)], payment = 120
Expected Output: false
Овој тест пример ја препокрива branch каде вкупниот збир на цените (вклучувајќи ги и попустите) е поголем од износот на уплатата.




5. Multiple Condition критериум


-Треба да се креират test cases што ќе ги покријат сите можни комбинации од boolean sub-expressions.
Условот if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0') содржи 3 под-услови:
item.getPrice() > 300
item.getDiscount() > 0
item.getBarcode().charAt(0) == '0'
Секој од овие  под-услови може да биде или true или false, што ни дава  2^3 = 8 можни комбинации. Треба сега да ги тестирам сите можни комбинации

Test Case 1: item.getPrice() <= 300, item.getDiscount() <= 0
Input: allItems = [new Item("item4", "068945", 280, 0.0f)], payment = 280
Expected Output: true
Овој тест пример ја препокрива branch каде ниту цената е поголема од 300 ниту попустот е поголем од 0

Test Case 2: item.getPrice() <= 300
Input: allItems = [new Item("item1", "078945", 300, 0.1f)], payment = 300
Expected Output: true
Овој тест пример ја препокрива branch каде цената не е поголема од 300

Test Case 3: item.getPrice() > 300, item.getDiscount() <= 0
Input: allItems = [new Item("item2", "098765", 320, 0.0f)], payment = 320
Expected Output: true
Овој тест пример ја препокрива branch во која цената е поголема од 300, но попустот не е поголем од 0

Test Case 4: item.getPrice() > 300, item.getDiscount() > 0, item.getBarcode().charAt(0) != '0'
Input: allItems = [new Item("item3", "178945", 350, 0.15f)], payment = 350
Expected Output: true
Овој тест пример ја препокрива branch каде цената е поголема од 300, попустот е поголем од 0, но баркодот не започнува со цифра 0

Test Case 5: item.getPrice() <= 300, item.getDiscount() > 0
Input: allItems = [new Item("item5", "078945", 300, 0.2f)], payment = 300
Expected Output: true
Овој тест пример ја препокрива branch каде цената не е поголема од 300, но попустот е поголем од 0

Test Case 6: item.getPrice() > 300, item.getDiscount() <= 0, item.getBarcode().charAt(0) != '0'
Input: allItems = [new Item("item6", "178945", 340, 0.0f)], payment = 340
Expected Output: true
Овој тест пример ја препокрива branch каде цената е поголема од 300, попустот не е поголем од 0 и баркодот не започнува со цифра 0.

Test Case 7: item.getPrice() <= 300, item.getDiscount() > 0, item.getBarcode().charAt(0) != '0'
Input: allItems = [new Item("item8", "178945", 290, 0.1f)], payment = 290
Expected Output: true
Овој тест пример ја препокрива branch каде цената не е поголема од 300, попустот е поголем од 0 и баркдот не започнува со цифрата 0.

Test Case 8: item.getPrice() > 300, item.getDiscount() > 0, item.getBarcode().charAt(0) == '0'
Input: allItems = [new Item("item7", "098765", 400, 0.25f)], payment = 350
Expected Output: false
Овој тест пример ја препокрива branch каде цената е поголема од 300, попустот е поголем од 0 и баркодот започнува со цифра 0. Ова треба да допринесе да се одземе 30 од сумата

