from io import BytesIO
from PIL import Image
import os
import re
import base64

image_folder = "c:\\Leonov\\GeniusLociData\\GL\\"
image_folder_output = "c:\\Leonov\\GeniusLociData\\GL_resized\\"
if not os.path.exists(image_folder_output):
    os.makedirs(image_folder_output)

listSizes = []
count = 0
pattern_image_tp = re.compile(r"_\D_")
width_optimal = 640
height_optimal = 320

for file in os.listdir(image_folder):
    image_tp_list = re.findall(pattern_image_tp, file)
    if len(image_tp_list) == 1:
        image_tp = image_tp_list[0].replace("_", "")
    else:
        continue
    # h, s, v
    w = 0
    h = 0
    if image_tp == "h":
        w = width_optimal
        h = height_optimal
    if image_tp == "v":
        w = height_optimal
        h = width_optimal
    if image_tp == "s":
        w = width_optimal
        h = width_optimal
    if w == 0 and h == 0:
        print(f"file={file}")
        continue

    image = Image.open(os.path.join(image_folder, file))
    # new_image = image.resize((w, h))
    image.thumbnail((width_optimal, width_optimal))
    image.save(os.path.join(image_folder_output, file))
    buffered = BytesIO()
    image.save(buffered, format="JPEG")
    img_str = base64.b64encode(buffered.getvalue())
    print(img_str)
    count = count + 1
    print(f"Processed {count} files")
    break

print(f"List count = {count}")
# print(listSizes)




