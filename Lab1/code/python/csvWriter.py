import csv

#a class to save test resolts
class Documentation:

    name =''
    
#set up and creat a csv file
#name is file name in a string. remember to end name with ".csv"
#tpy is a string with the name on a the non constant test value
#setings thet wer used in a string
    def __init__(self, name, typ, setings):
        self.name = name
        with open(name, 'w') as fp:
            wr = csv.writer(fp, lineterminator='\n')    
            wr.writerow(['k;Time in;Time B;' + setings])
            
#print the new row
#valus mast be a list witt 3 ints
#[test value, insertensort time, binsertionsort time]
    def write(self, valus):
        with open(self.name, 'a') as fp:
            wr = csv.writer(fp, lineterminator='\n')
            wr.writerow(self.formating(valus))
        

#formating a list whit len 3 to a printebole list
    def formating(self, arr):
        return [str(arr[0])+';'+str(arr[1])+';'+str(arr[2])]
