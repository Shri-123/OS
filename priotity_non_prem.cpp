#include <iostream>
using namespace std;

int main()
{
    int a[10] = {0, 1, 2, 3, 4};
    int b[10] = {4, 3, 1, 5, 2};
    int x[10], pr[10] = {2, 3, 4, 5, 5};
    int waiting[10], turnaround[10], completion[10];
    int i, j, smallest, count = 0, time, n=5;
    double avg = 0, tt = 0, end;

    for (i = 0; i < n; i++)
        x[i] = b[i];

    pr[9] = -1;

    for (time = 0; count != n; time++)
    {
        smallest = 9;
        for (i = 0; i < n; i++)
        {
            if (a[i] <= time && pr[i] > pr[smallest] && b[i] > 0)
                smallest = i;
        }

        time += b[smallest] - 1;
        b[smallest] = -1;
        count++;
        end = time + 1;
        completion[smallest] = end;
        waiting[smallest] = end - a[smallest] - x[smallest];
        turnaround[smallest] = end - a[smallest];
    }

    cout << "Process"
         << "\t"
         << "   Burst-time"
         << "\t"
         << "Arrival-time"
         << "\t"
         << "Waiting-time"
         << "\t"
         << "Turnaround-time"
         << "\t"
         << "Completion-time"
         << "\t"
         << "Priority" << endl;
    cout << ("---------------------------------------------------------------------------------------------------\n");

    for (i = 0; i < n; i++)
    {
        cout << "P" << i + 1 << "\t\t" << x[i] << "\t\t" << a[i] << "\t\t" << waiting[i] << "\t\t" 
        << turnaround[i] << "\t\t" << completion[i] << "\t\t" << pr[i] << endl;
        cout << ("--------------------------------------------------------------------------------------------------\n");
        avg = avg + waiting[i];
        tt = tt + turnaround[i];
    }

    cout << "\n\nAverage waiting time =" << avg / n << endl;
    cout << "Average Turnaround time =" << tt / n << endl;
}