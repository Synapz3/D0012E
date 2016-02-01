import random
import time
import sys
import csvWriter

#Hint: all functions must be over main code

def insertionSort(arr):
    SortedArr = []
    index = 0
    added = False
    for element in arr:
        for item in SortedArr:
            if element <= item and not added:
                SortedArr.insert(SortedArr.index(item), element)
                added = True
        if not added:
            SortedArr.append(element)
        added = False
    return SortedArr

def binsertionSort(arr):
    SortedArr = []
    for element in arr:
       SortedArr.insert(binSearch(SortedArr,element), element)
    return SortedArr

def binSearch(arr, element):
    size = len(arr)
    half = int(size/2)
    if size == 0:
        return 0
    elif size == 1:
        if element < arr[0]:
            return 0
        else:
            return 1
    elif element == arr[half]:
        return half
    elif element < arr[half]:
        return binSearch(arr[:half], element)
    else:
        return half+1 + binSearch(arr[half+1:], element)

def createArr(size,maxNum):
    arr = []
    n = 0
    while (n < size):
        arr.append(random.randint(0, maxNum))
        n += 1
    return arr    


#The
#Real
#Deal
def merge_sort(arr,k,algo):

    ArrSize = len(arr)
    if k == 0:
        raise ValueError('k can not be 0!')
        return []
    elif ArrSize <= k:
        return algo(arr)
    else:
        _right =    merge_sort(arr[:ArrSize//2],k,algo)
        _left  =    merge_sort(arr[ArrSize//2:],k,algo)
        return merge(_right,_left)

#merge to lists to gader
def merge(arr1_,arr2_):
    returnArr = []
    arr1_i = 0
    arr2_i = 0
    arr1_size = len(arr1_)
    arr2_size = len(arr2_)
    if arr1_i >= arr1_size:                 #test if a list is empty
        return returnArr + arr2_[arr2_i:]
    elif arr2_i >= arr2_size:
        return returnArr + arr1_[arr1_i:]
    while True:                             #loop until one of the lists are empty
        if arr1_[arr1_i] < arr2_[arr2_i]:
            returnArr.append(arr1_[arr1_i]) #add element to return list from start list
            arr1_i += 1
            if arr1_i >= arr1_size:         #when the one list is added to return
                return returnArr + arr2_[arr2_i:] #add rest of the other list
        else:
            returnArr.append(arr2_[arr2_i]) #add element to returnlist
            arr2_i += 1
            if arr2_i >= arr2_size:         #a the list is added to return list
                return returnArr + arr1_[arr1_i:] #add rest of the other list

def test(arr,k,times,algo):
    result = 0.0
    count = 0
    while count < times:
        startTime = time.time()
        merge_sort(arr,k,algo)
        result += (time.time()-startTime)
        count += 1
    return result/float(times)

#Main code

#Error checks
if len(sys.argv) == 7:
    print("k_start set to: "+sys.argv[1])
    k_start = int(sys.argv[1])              #first k value to test
    print("k_max set to: "+sys.argv[2])
    k_max = int(sys.argv[2])                #last k value to test
    print("ArrMinSize set to: "+sys.argv[3])
    current = int(sys.argv[3])              #arrMin size
    print("ArrMaxSize set to: "+sys.argv[4])
    maxi = int(sys.argv[4])                 #arrMax size
    print("MaxNum set to: "+sys.argv[5])
    maxNum = int(sys.argv[5])               #MaxNum in Arr
    print("SpeedTest: "+sys.argv[6])
    speedTest = bool(sys.argv[6])           #MaxNum in Arr

else:
    print("Not enough arguments taken, \n"+
          "k_start = 1000 \nk_max =5000 \ncurrent = 5000 \nmaxi = 20000 \nmaxNum=100 by default")
    k_start   = 1000
    k_max     = 10000
    current   = 10000
    maxi      = 10000
    maxNum    = 100
    speedTest = False


resultList = [0.0,0.0,0.0]
test_nr = 0
arrSize = current
while arrSize <= maxi:
    arr = createArr(arrSize,maxNum)
    print('arrSize: ' + str(arrSize))
    #test k valus from count to times and save in ktest.csv
    count = k_start
    write = csvWriter.Documentation('test_n'+str(arrSize)+'_k'+str(k_start)+'-'+str(k_max)+'.csv', 'k', 'k '+str(k_start)+'-'+str(k_max)+' n='+ str(arrSize))
    arrSize *= 10
    while count <= k_max:
        resultList[0] = count
        if speedTest:
            resultList[1] = test(arr[:],count,1,insertionSort)
        else:
            resultList[1] = test(arr[:],count,10,insertionSort)
        print ('k: '+str(count)+' : insertionSort : ' + str(resultList[1]))

        if speedTest:
            resultList[2] = test(arr[:],count,1,binsertionSort)
        else:
            resultList[2] = test(arr[:],count,10,binsertionSort)
        print ('k: '+str(count)+' : binsertionSort: ' + str(resultList[2]))
        write.write(resultList)
        count += 1000



#print (mergeArr(SortedArr))
#print(insertionSort(arr))
#print ("insertsonSort delta time: %s " % (time.time()-startTime)) 
#print (insertionSort(arr))
