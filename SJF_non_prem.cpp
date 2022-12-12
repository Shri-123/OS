#include <stdio.h>
using namespace std;
#include<bits/stdc++.h>
int main()
{
    int bt[20], p[20], wt[20], tat[20], i, j, n, total = 0, pos, temp;
    float avg_wt, avg_tat;
    printf("Enter number of process:");
    scanf("%d", &n);

    printf("\nEnter Burst Time:\n");
    for (i = 0; i < n; i++)
    {
        printf("P%d:", i + 1);
        scanf("%d", &bt[i]);
        p[i] = i + 1;
    }

    // sorting of burst times
    for (i = 0; i < n; i++)
    {
        pos = i;
        for (j = i + 1; j < n; j++)
        {
            if (bt[j] < bt[pos])
                pos = j;
        }
        swap(bt[i], bt[pos]);
        swap(p[i], p[pos]);
    }

    wt[0] = 0;

    for (i = 1; i < n; i++)
    {
        wt[i] = 0;
        for (j = 0; j < i; j++)
            wt[i] += bt[j];

        total += wt[i];
    }

    avg_wt = (float)total / n;
    total = 0;

    printf("\nProcess   Burst Time  Waiting Time    Turnaround Time\n");
    printf("------------------------------------------------------------------------------------");
    for (i = 0; i < n; i++)
    {
        tat[i] = bt[i] + wt[i];
        total += tat[i];
        printf("\n P%d\t%d\t%d\t%d", p[i], bt[i], wt[i], tat[i]);
        printf("\n------------------------------------------------------------------------------------");
    }

    avg_tat = (float)total / n;
    printf("\n\nAverage Waiting Time = %f", avg_wt);
    printf("\nAverage Turnaround Time = %f\n", avg_tat);
}