<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableCourriers extends AbstractMigration
{

    public function change(): void
    {
        $couriers = $this->table('courriers', ['signed' => false]);
        $couriers->addColumn('title', 'string', ['limit' => 80])
            ->create();
    }
}
