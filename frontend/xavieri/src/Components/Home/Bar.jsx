import { BarChart } from '@mui/x-charts/BarChart';
import { axisClasses } from '@mui/x-charts/ChartsAxis';

const currency='sek'
const valueFormatter = (value) => `${value+" "+currency}`;

const chartSetting = {
  yAxis: [
    {
      label: 'amount (sek)',
    },
  ],
  series: [{ dataKey: 'amount', label: 'My money', valueFormatter }],
  height: 300,
  sx: {
    [`& .${axisClasses.directionY} .${''}`]: {
      transform: 'translateX(-10px)',
    },
  },
};

const MoneyChart=({data})=>{
  return (
    <div style={{ width: '100%' }}>
      <BarChart
        dataset={data}
        xAxis={[{ scaleType: 'band', dataKey: 'friend', tickPlacement:'middle' }]}
        {...chartSetting}
      />
    </div>
  );
}
export default MoneyChart
