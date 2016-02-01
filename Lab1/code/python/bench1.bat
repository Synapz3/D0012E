echo "Comencing test!"
start py -2  main.py 1 100 100 100 1000000 False
start py -2  main.py 1 1000 1000 1000 1000000 True
start py -2  main.py 1 10000 10000 10000 1000000 True
echo "Done!"
pause