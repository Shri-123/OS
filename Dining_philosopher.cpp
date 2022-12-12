
// DINING PHILOSOPHER USING SEMAPHORES.

#include <iostream>
#include <bits/stdc++.h>

using namespace std;

void init(vector<int> &semaphores)
{
    for (int i = 0; i < 5; i++)
    {
        semaphores[i] = 0;
    }
}

int main()
{
    
    int n = 6;
    vector<int> semaphores(n, 0);
    int philosopher[5] = {0, 1, 2, 3, 4};
    int chopsticks[5] = {0, 1, 2, 3, 4};
 

    for (int i = 0; i < n; i++)
    {
        cout << "\nConsidering " << i << " philosopher is eating....." << endl;
        
        semaphores[i % n] = 1;
        semaphores[(i + 1) % n] = 1;

        cout << "Chopsticks " << i << " and " << (i + 1) % n << " are used by philosopher " << i << endl;

        for (int j = 0; j < n; j++)
        {
            if (j!=i)
            {
                cout << "\nConsidering " << j << " philosopher is eating....." << endl;
                if (semaphores[j % n] == 0 and semaphores[(j + 1) % n] == 0)
                {
                    cout << "Chopsticks " << j << " and " << (j + 1) % n << " are used by philosopher and he can eat" << endl;
                }

                else
                {
                    cout << "Either of Chopsticks are in use with adjacent Philosopher..." << endl;
                    cout << "So " << j << " philosopher is not able to eat" << endl;
                    cout << "Sorry!!!!!!!\n" << endl;
                }
            }
        }

        cout << endl << "-----------------------------------------------------------------------"
             << endl;
        init(semaphores);
    }

    return 0;
}