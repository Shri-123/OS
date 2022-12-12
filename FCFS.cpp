#include <iostream>
using namespace std;

// WAITING TIME -
    void findWaitingTime(int processes[], int n,
                        int bt[], int wt[])
    {
        wt[0] = 0;

        for (int i = 1; i < n; i++)
            wt[i] = bt[i - 1] + wt[i - 1];
    }

    // TURNAROUND TIME
    void findTurnAroundTime(int processes[], int n,
                            int bt[], int wt[], int tat[])
    {

        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
    }

    // AVERAGE TIME
    void findavgTime(int processes[], int n, int bt[])
    {
        int wt[n], tat[n], total_wt = 0, total_tat = 0;

        findWaitingTime(processes, n, bt, wt);

        findTurnAroundTime(processes, n, bt, wt, tat);

        cout << "Processes  "
            << " Burst time  "
            << " Waiting time  "
            << " Turn around time\n";
        cout << ("-------------------------------------------------------------\n");

        for (int i = 0; i < n; i++)
        {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            cout << "   "
                << "P" << i + 1 << "\t\t" << bt[i] << "\t    "
                << wt[i] << "\t\t  " << tat[i] << endl;
            //cout << ("-------------------------------------------------------------\n");
        }

        cout << "Average waiting time = "
            << (float)total_wt / (float)n;
        cout << "\nAverage turn around time = "
            << (float)total_tat / (float)n;
    }

    // MAIN METHOD
    int main()
    {
        // process id's
        int processes[] = {1, 2, 3,4};
        int n = sizeof processes / sizeof processes[0];

        // Burst time of all processes
        int burst_time[] = {2, 2, 3, 4};

        findavgTime(processes, n, burst_time);

        return 0;
    }