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
    icon: 'list-alt',
  },
  {
    id: 3,
    text: 'Orders',
    path: '/',
    icon: 'list-alt',
    items:[
      {id:'mec', path:'orders/mechanical', text:'Mechanical'},
      {id:'elec', path:'orders/electrical', text:'Electrical'},
      {id:'body', path:'orders/bodywork', text:'BodyWork'},
    ]
  },
  {
    id: 4,
    text: 'Shop',
    path: '/shop',
    icon: 'list-alt',
  },
];
