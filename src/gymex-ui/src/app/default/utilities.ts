export function enumSelector(definition) {
  return Object.keys(definition)
    .map(key => ({value: definition[key], title: key}));
}

export function unique(value, index, self) {
  return self.indexOf(value) === index;
}

export function sameDay( d1, d2 ){
  return d1.getUTCFullYear() == d2.getUTCFullYear() &&
    d1.getUTCMonth() == d2.getUTCMonth() &&
    d1.getUTCDate() == d2.getUTCDate();
}
