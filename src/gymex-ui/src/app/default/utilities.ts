export function enumSelector(definition) {
  return Object.keys(definition)
    .map(key => ({value: definition[key], title: key}));
}

export function unique(value, index, self) {
  return self.indexOf(value) === index;
}
