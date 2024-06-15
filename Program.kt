import java.time.LocalDate
import java.time.Period
import com.google.gson.Gson
import java.io.File



class Display {

    var tasks: MutableList<Task> = mutableListOf();

    
    fun addtask(task: Task){
        tasks.add(task)

    }

    fun display_tasks(){
        for (i in 0 until tasks.size){
            print(String.format("%d- Goal name: %s \n Goal Description : %s\n Goal Due Date : %d/%d/%d \n", (i+1), tasks[i].task_name, tasks[i].task_description, tasks[i].due_date.month, tasks[i].due_date.day, tasks[i].due_date.year))
        }

    }
    fun remove_task()
    {   
        for (i in 0 until tasks.size){
            print(String.format("%d- Goal name: %s \n Goal Description : %s\n Goal Due Date : %d days, %d months and %d years \n", (i+1), tasks[i].task_name, tasks[i].task_description, tasks[i].due_date.month, tasks[i].due_date.day, tasks[i].due_date.year))
        }
        println("Select one number above:")
        var value = readLine()!!
        tasks.removeAt(value.toInt()-1)
    }
    fun tasks_by_duedate(){
        for (i in 0 until tasks.size){
            var time = LocalDate.of(tasks[i].due_date.year, tasks[i].due_date.month, tasks[i].due_date.day)
            var currentdate = LocalDate.now()
            val difference = Period.between(currentdate, time)
        println()


            if (difference.years == 0 && difference.months == 0 && difference.days>-1 && difference.days<4){
                print(String.format("Goal name: %s \nGoal Description : %s\nTime to due Date : %d day\n", tasks[i].task_name, tasks[i].task_description, difference.days))
            }

        }
    }
}

class Task(name: String, descrition: String, date: Date) {
    val task_name = name;
    val task_description = descrition
    val due_date = date



}
class Date(day: Int, month: Int, year: Int){
    val day = day
    val month = month
    val year = year

    fun date_formatted(){
        print(day)
    }
}



fun main(){
    var main_display = Display()
    println("Welcome to the task scheduler, select one option:")
    while (true)
    {
        println("===============================")
        println("1- Add a new task")
        println("2- See all task")
        println("3- Remove task")
        println("4- Task to the next 3 days")
        println("5- Save")
        println("6- Load")
        println("7- Exit")

        val option = readLine()
        if (option == "7")
        {
            println("Good Bye!")
            break
        
        }
        else if(option == "6")
        {
            val gson = Gson()
            val file = File("data.txt")
            val loadedata = file.readText()
            main_display = gson.fromJson(loadedata, Display::class.java)


        }
        else if(option == "5")
        {
            val gson = Gson()
            val file = File("data.txt")
            if (file.exists()){
                while (true)
                {
                println("You already has a save, are you sure that you want overwrite it? (Y/N)")
                val userinput = readLine()!!
                if (userinput == "Y")
                    {
                    val json = gson.toJson(main_display)
                    file.writeText(json)
                    break
                    }
                    else if(userinput =="N"){
                        break
                    }
                }

            }




        }
        else if(option == "4")
        {
            main_display.tasks_by_duedate()


        }
        else if(option == "3")
        {
            
            main_display.remove_task()

        }
        else if(option == "2")
        {
            main_display.display_tasks()
            println("Press any key to return to main menu")
            var task_name = readLine()!!


        }
        else if(option == "1")
        {
            print("Type your task name: ")
            var task_name = "";
            var description = "";
            while (true)
            {
                task_name = readLine()!!
                if (task_name.isNotEmpty()){
                    
                    break
                }

                println("Please, enter a valid task name:")
            }
            
            
            print("Describe your task: ")
            while (true){
                description = readLine()!!
                if (description.isNotEmpty()){
                    break
                }
                    
                println("Please, enter a valid description:")
            }
           
            
            while (true)
            {
            println("Select the due date of the tasks")
            print("Year: ")
            var year = readLine()!!
            print("Month: ")
            var month = readLine()!!
            print("day: ")
            var day = readLine()!!

                try {
                    var time = Date(day.toInt(),month.toInt(),year.toInt())    
                    var mytask = Task(task_name, description, time)
                    main_display.addtask(mytask)
                    break
                }catch(e : Exception){

                    println("Please, type a valid date")
                }

            }
            


        }
        else
        {
            println("Please, type a valid command.")

        }
     


    }
}
