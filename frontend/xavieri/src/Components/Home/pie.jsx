import * as React from 'react';
import { PieChart } from '@mui/x-charts/PieChart';

const pieData=[
  {  value: 10, label: 'series A', origin:1 },
  {  value: 15, label: 'series B' },
  {  value: 20, label: 'series C' },
]

const expensesPie=()=> {
  return (
    <PieChart
      series={[
        {
            data: pieData,
            innerRadius: 30,
            outerRadius: 80,
            paddingAngle: 5,
            cornerRadius: 5,
        },
      ]}
      width={400}
      height={200}
    />
  );
}

export default expensesPie