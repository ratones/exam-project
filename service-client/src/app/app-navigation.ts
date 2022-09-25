export const navigation = [
  {
    id: 1,
    text: 'Home',
    path: '/home',
    icon: 'home',
  },
  {
    id: 2,
    text: 'Vehicles',
    path: '/vehicles',
    icon: 'car',
  },
  {
    id: 3,
    text: 'Orders',
    path: '',
    icon: 'preferences',
    items:[
      {id:'mec', path:'orders/mechanical', text:'Mechanical', icon:'folder'},
      {id:'elec', path:'orders/electrical', text:'Electrical', icon:'folder'},
      {id:'body', path:'orders/bodywork', text:'BodyWork', icon:'folder'},
    ]
  },
  {
    id: 4,
    text: 'Shop',
    path: '/shop',
    icon: 'box',
  },
];
