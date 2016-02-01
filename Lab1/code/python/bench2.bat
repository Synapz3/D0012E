echo "Comencing test!"
start py -2  main.py 1000 50000 50000 50000 1000000 False
start py -2  main.py 50000 100000 100000 100000 1000000 True
start py -2  main.py 100000 150000 150000 150000 1000000 True
start py -2  main.py 150000 200000 200000 200000 1000000 True
echo "Done!"
pause