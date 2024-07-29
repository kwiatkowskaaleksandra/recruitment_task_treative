import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModules } from './app/app.modules';
import { registerables } from 'chart.js';
import { Chart } from 'chart.js';

Chart.register(...registerables);

platformBrowserDynamic().bootstrapModule(AppModules)
  .catch(err => console.error(err));
