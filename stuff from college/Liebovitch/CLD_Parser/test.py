import xml.etree.ElementTree as ET
tree = ET.parse('country_data.xml')
root = tree.getroot()

print(root.tag)

# print list of child and attributes
for child in root:
    print(child.tag, child.attrib)

for neighbor in root.iter('p:cNvPr'):
    print(neighbor.attrib)
